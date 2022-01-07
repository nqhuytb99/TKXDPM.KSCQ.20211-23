package views.screen.bike;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import common.SingletonObject;
import common.exception.InvalidStationException;
import controller.StationController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.PaymentModel;
import model.StationModel;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.payment.PaymentScreenHandler;

public class ReturnBikeDetailScreen extends BaseScreenHandler implements Initializable {
	
	private final String ERROR_EMPTY_STATION = "Bạn chưa chọn bãi xe để trả !!";
	private final String CHOOSE_STATION_TEXT = "Choose Station";
	
	@FXML
	private Label nameStation;
	
	@FXML
	private Label nameBike;
	
	@FXML
	private Label typeBike;
	
	@FXML
	private Label depositPrice;
	
	@FXML
	private Label licensePlateBike;
	
	@FXML
	private Label manuafacturingDate;
	
	@FXML
	private Label costBike;
	
	@FXML
	private Label producer;
	
	@FXML
	private Label battery;
	
	@FXML
	private Label loadCycles;
	
	@FXML
	private Label bikeRetalTime;
	
	@FXML
    private ComboBox comboBoxReturnStation;
	
	@FXML
	private Label errorReturnBike;
	
	private PaymentModel payment;
	
	private List<StationModel> stationModels;
	
	private BaseScreenHandler thisScreen = this;
	
	public ReturnBikeDetailScreen(Stage stage, String screenPath, PaymentModel payment) throws IOException {
		super(stage, screenPath);
		this.payment = payment;
		errorReturnBike.setVisible(false);
		comboBoxReturnStation.setValue(CHOOSE_STATION_TEXT);
		
		// init comboBox for station
		ObservableList<String> stationObservable = FXCollections.observableArrayList();
		
		StationController stationController = new StationController();
		List<StationModel> stations = stationController.findAll();
		this.stationModels = stations;
		
		stations.forEach(item -> {
			stationObservable.add(item.getName());
		});
		
		comboBoxReturnStation.setItems(stationObservable);
		
		
		if (Objects.nonNull(payment.getStationModel()) && Objects.nonNull(payment.getStationModel().getName())) {
			nameStation.setText(payment.getStationModel().getName());
		}
		
		if (Objects.nonNull(payment.getBikeModel()) && Objects.nonNull(payment.getBikeModel().getName())) {
			nameBike.setText(payment.getBikeModel().getName());
		}
		
		if (Objects.nonNull(payment.getBikeModel()) && Objects.nonNull(payment.getBikeModel().getType())) {
			typeBike.setText(payment.getBikeModel().getType().toUpperCase());
		}
		
		if (Objects.nonNull(payment.getBikeModel()) && Objects.nonNull(payment.getBikeModel().getLicense_plate())) {
			licensePlateBike.setText(payment.getBikeModel().getLicense_plate());
		}
		
		if (Objects.nonNull(payment.getDeposit_price())) {
			depositPrice.setText(payment.getDeposit_price().toString());
		}
		
		if (Objects.nonNull(payment.getRent_time())) {
			bikeRetalTime.setText(payment.getRent_time().toLocalDateTime().toString());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		comboBoxReturnStation.valueProperty().addListener(new ChangeListener<String>() {
	        @Override public void changed(ObservableValue ov, String t, String name) {
	            if (name != null && name != "") {
	            	if (Objects.equals(name, CHOOSE_STATION_TEXT)) {
	            		
	            	} else {
	            		Optional<StationModel> stationModel = stationModels.stream()
		            			.filter(item -> Objects.equals(item.getName(), name)).findFirst();
		            	if (stationModel.isPresent()) {
		            		StationModel station = stationModel.get();
		            		if (station.getNum_of_empty_docks() == 0) {
		            			errorReturnBike.setVisible(true);
		            			throw new InvalidStationException("Station is empty");
		            		} else {
		            			errorReturnBike.setVisible(false);
		            			payment.setReturnStation(station);
		            		}
		            	}
	            	}
	            	
	            } else {
	            	errorReturnBike.setText(ERROR_EMPTY_STATION);
	            	errorReturnBike.setVisible(true);
	            }
	        }    
	    });
	}
	
	@FXML
	public void backScreen(MouseEvent event) throws IOException {
		SingletonObject instance = SingletonObject.getInstance();
		BaseScreenHandler previousScreen = instance.getPreviousScreen();
		previousScreen.show();
	}
	
	@FXML
	public void paymentBike(MouseEvent event) throws IOException {
		PaymentScreenHandler paymentScreenHandler = new PaymentScreenHandler(stage, Configs.PAYMENT, this.payment, thisScreen);
		paymentScreenHandler.setScreenTitle("Payment Screen");
		SingletonObject.getInstance().addScreen(thisScreen);
		paymentScreenHandler.show();
	}
}
