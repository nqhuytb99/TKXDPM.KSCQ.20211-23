package views.screen.payment;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

import common.SingletonObject;
import controller.BikeController;
import controller.PaymentController;
import controller.StationController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.BikeModel;
import model.PaymentModel;
import model.StationModel;
import utils.Configs;
import utils.TypePayment;
import views.screen.BaseScreenHandler;
import views.screen.result.ResultScreenHandler;

public class PaymentScreenHandler extends BaseScreenHandler implements Initializable {
	
	private final Integer defaultPrice = 10000;
	
	private final Integer defaultPricePerFifteenMinute = 3000;
	
	@FXML
	private TextField tfCardNumber;
	@FXML
	private TextField tfHolderName;
	@FXML
	private TextField tfExpirationDate;
	@FXML
	private TextField tfSecurityCode;
	@FXML
	private Label lbAmount;
	
	@FXML
	private Label paymentMethod;
	
	private TypePayment typePayment;

	private BikeModel bike;
	private PaymentModel payment;
	private BaseScreenHandler screen;
	private Integer priceRentingBike;

	private BaseScreenHandler thisScreen = this;
	public <T> PaymentScreenHandler(Stage stage, String screenPath, T object, BaseScreenHandler screen) throws IOException {
		super(stage, screenPath);
		this.screen = screen;
		if (object instanceof BikeModel) {
			this.bike = (BikeModel) object;
			lbAmount.setText(calDeposit(bike.getType()).toString());
			this.typePayment = TypePayment.PAY;
		}
		
		if (object instanceof PaymentModel) {
			this.payment = (PaymentModel) object;
			this.typePayment = TypePayment.REFUND;
			this.priceRentingBike = calRefundOrder(payment);
			lbAmount.setText(priceRentingBike.toString());
			paymentMethod.setText("Amount Refund");
		}
		// TODO Auto-generated constructor stub
	}
	
	// Event Listener on Button[#btnConfirmPayment].onMouseClicked
	@FXML
	public void onConfirm(MouseEvent event) throws IOException {
		// call api thanh toan
		String contents = "pay order";
		String refundContent = "Refund order";
		PaymentController ctrl = new PaymentController();
		BikeController bikeController = new BikeController();
		StationController stationController = new StationController(); 
		Map<String, String> response = null;
		if (Objects.equals(this.typePayment, TypePayment.PAY)) {
			response = ctrl.payOrder(calDeposit(bike.getType()), contents, tfCardNumber.getText(), tfHolderName.getText(),
					tfExpirationDate.getText(), tfSecurityCode.getText(), TypePayment.PAY);
		} else {
			response = ctrl.payOrder(this.priceRentingBike, refundContent, tfCardNumber.getText(), tfHolderName.getText(),
					tfExpirationDate.getText(), tfSecurityCode.getText(), TypePayment.REFUND);
		}
		
		if(response.get("RESULT").equals("PAYMENT SUCCESSFUL!")) {
			// update bike, station
			if (Objects.equals(typePayment, TypePayment.PAY)) {
				bike.setStatus(1);
				bikeController.updateBike(bike);	
				StationModel station = new StationModel();
				station = stationController.findById(bike.getId_station());
				station.setNum_of_empty_docks(station.getNum_of_empty_docks() + 1);
				if(bike.getType().equals("standard")) {
					station.setNum_of_bikes(station.getNum_of_bikes() - 1);
				}
				else if(bike.getType().equals("electric")) {
					station.setNum_of_ebikes(station.getNum_of_ebikes() - 1);
				}
				else station.setNum_of_twinbikes(station.getNum_of_twinbikes() - 1);
				stationController.updateStation(station);
				// insert payment
				PaymentModel newPayment = new PaymentModel();
				newPayment.setId_bike(bike.getId());
				newPayment.setId_station_rent(bike.getId_station());
				newPayment.setDeposit_card_number(tfCardNumber.getText());
				newPayment.setStatus(1);
				newPayment.setDeposit_price(calDeposit(bike.getType()));
				ctrl.savePayment(newPayment);
			}
			
			if (Objects.equals(typePayment, TypePayment.REFUND)) {
				// update empty docks station
				StationModel stationReturn = this.payment.getReturnStation();
				stationReturn.setNum_of_empty_docks(stationReturn.getNum_of_empty_docks() - 1);
				stationController.updateStation(stationReturn);
				
				// update payment
				this.payment.setReturn_time(Timestamp.from(Instant.now()));
				this.payment.setStatus(2);
				this.payment.setRent_price(this.priceRentingBike);
				ctrl.updatePaymentAfterRefund(this.payment);
			}
		}
		BaseScreenHandler resultScreen = new ResultScreenHandler(this.stage, Configs.RESULT_SCREEN_PATH, response.get("RESULT"), response.get("MESSAGE") );
		resultScreen.setPreviousScreen(this);
		resultScreen.setScreenTitle("Result Screen");
		resultScreen.show();
	}
	// Event Listener on Button[#btnBack].onMouseClicked
	@FXML
	public void onBack(MouseEvent event) throws IOException {
		// TODO Autogenerated
//		BikeScreenHandler bikeScreenHandler = new BikeScreenHandler(stage, ConfigsBIKE, bike);
//		bikeScreenHandler.setScreenTitle("Bike Screen");
//		bikeScreenHandler.show();
		SingletonObject instance = SingletonObject.getInstance();
		BaseScreenHandler previousScreen = instance.getPreviousScreen();
		previousScreen.show();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	private Integer calRefundOrder(PaymentModel payment) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Instant rentBikeTime = payment.getRent_time().toInstant();
		Duration duration = Duration.between(rentBikeTime, Instant.now());
		Integer totalMinuteRentBike = Integer.valueOf((int) duration.toMinutes());
		Integer priceRent = 0;
		
		// if rentingTime < 0 => free
		if (0 < totalMinuteRentBike && totalMinuteRentBike <= 10) {
			priceRent = 0;
		} else if (10 <= totalMinuteRentBike && totalMinuteRentBike <= 30) {
			priceRent = defaultPrice;
		} else {
			priceRent = defaultPrice + ((totalMinuteRentBike - 30) / 15) * defaultPricePerFifteenMinute;
		}
		
		Integer amountRefund = payment.getDeposit_price() - priceRent;
		if (amountRefund <= 0) {
			return 0;
		}
		return payment.getBikeModel().getType().equals("standard") ? amountRefund : Integer.valueOf((int) (amountRefund * 1.5));
	}
	
	private Integer calDeposit(String type) {
		if(type.equals("standard")){
			return 40000;
		}
		else if(type.equals("electric")) {
			return 70000;
		}
		else return 55000;
	}
}
