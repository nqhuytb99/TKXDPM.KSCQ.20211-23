package views.screen.bike;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import common.SingletonObject;
import controller.BikeController;
import controller.PaymentController;
import controller.StationController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.BikeModel;
import model.PaymentModel;
import model.StationModel;
import utils.Configs;
import views.screen.BaseScreenHandler;

public class ReturnBikeScreenHandler extends BaseScreenHandler implements Initializable {
	
	@FXML
	private TableView<PaymentModel> listPaymentBike;
	
	@FXML
	private TableColumn<PaymentModel, Integer> id;
	
	@FXML
	private TableColumn<PaymentModel, String> stationName;
	
	@FXML
	private TableColumn<PaymentModel, String> bikeName;
	
	@FXML
	private TableColumn<PaymentModel, String> licensePlate;
	
	@FXML
	private TableColumn<PaymentModel, String> bikeType;
	
	@FXML
	private TableColumn<PaymentModel, String> rentingTime;
	
	@FXML
	private TableColumn<PaymentModel, Integer> depositPrice;
	
	@FXML
	private TableColumn<PaymentModel, String> depositCardNumber;
	
	private BaseScreenHandler screen;
	
	private BaseScreenHandler thisScreen = this;
	
	public ReturnBikeScreenHandler(Stage stage, String screenPath, BaseScreenHandler screen) throws IOException {
		super(stage, screenPath);
		this.screen = screen;
		initInformation();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		stationName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStationModel().getName()));
		bikeName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBikeModel().getName()));
		licensePlate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBikeModel().getLicense_plate()));
		bikeType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBikeModel().getType()));
		rentingTime.setCellValueFactory(new PropertyValueFactory<>("rent_time"));
		depositPrice.setCellValueFactory(new PropertyValueFactory<>("deposit_price"));
		depositCardNumber.setCellValueFactory(new PropertyValueFactory<>("deposit_card_number"));
		
		listPaymentBike.setRowFactory(tv -> {
		    TableRow<PaymentModel> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY && event.getClickCount() == 1) {
		        	PaymentModel clickedRow = row.getItem();
		    		try {
		    			BaseScreenHandler returnBikeDetailScreenHandler = new ReturnBikeDetailScreen(stage, Configs.RETURN_BIKE_DETAIL, clickedRow);
		    			returnBikeDetailScreenHandler.setScreenTitle("Return Bike Detail Screen");
		    			returnBikeDetailScreenHandler.show();
		    			SingletonObject.getInstance().addScreen(thisScreen);
		    		} catch (IOException e) {
		    			e.printStackTrace();
		    		}
		        }
		    });
		    return row ;
		});
	}
	
	@FXML
	public void backScreen(MouseEvent event) throws IOException {
		SingletonObject instance = SingletonObject.getInstance();
		BaseScreenHandler previousScreen = instance.getPreviousScreen();
		previousScreen.show();
	}
	
	/**
	 * Init data of payment
	 * 
	 */
	private void initInformation() {
		PaymentController paymentController = new PaymentController();
		List<PaymentModel> payments = paymentController.getAllPaymentNeedReturn();
		
		// get all list bike
		BikeController bikeController = new BikeController();
		List<BikeModel> bikes = bikeController.getAllBike();
		
		// get all list station
		StationController stationController = new StationController();
		List<StationModel> stations = stationController.findAll();
		
		// enrich station and bike
		payments.forEach(payment -> {
			Optional<BikeModel> bikeOptional = bikes.stream()
					.filter(bike -> Objects.equals(bike.getId(), payment.getId_bike()))
					.findFirst();
			if (bikeOptional.isPresent()) {
				payment.setBikeModel(bikeOptional.get());
			}
			
			Optional<StationModel> stationOptional = stations.stream()
					.filter(station -> Objects.equals(station.getId(), payment.getId_station_rent()))
					.findFirst();
			if (bikeOptional.isPresent()) {
				payment.setStationModel(stationOptional.get());
			}
		});
		
		if (Objects.nonNull(payments)) {
			listPaymentBike.setItems(FXCollections.observableList(payments));
		}
	}
}
