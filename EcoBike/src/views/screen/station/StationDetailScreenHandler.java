package views.screen.station;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import common.SingletonObject;
import controller.BikeController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.BikeModel;
import model.StationModel;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.bike.BikeScreenHandler;
public class StationDetailScreenHandler  extends BaseScreenHandler implements Initializable{
		
	@FXML
	private Label lbName;
	
	@FXML
	private Label lbAddress;
	
	@FXML
	private Label lbNumOfBike;
	
	@FXML
	private Label lbNumOfEbike;
	
	@FXML
	private Label lbNumOfTwinbike;
	
	@FXML
	private Label lbOfEmptyDock;
	
	@FXML
	TableView<BikeModel> tbListBike;
	
	@FXML 
	TableColumn<BikeModel, Integer> id;
	
	@FXML 
	TableColumn<BikeModel, String> name;
	
	@FXML 
	TableColumn<BikeModel, String> type;
	
	@FXML 
	TableColumn<BikeModel, Integer> weight;
	
	@FXML 
	TableColumn<BikeModel, Integer> cost;
	
	private StationModel stationModel;
	
	private BaseScreenHandler thisScreen = this;
	
	public StationDetailScreenHandler(Stage stage, String screenPath, StationModel stationModel) throws IOException{
		super(stage, screenPath);
		this.stationModel = stationModel;
		setupInfomation();
	}

	// Event Listener on Button[#btnBack].onMouseClicked
	@FXML
	public void backScreen(MouseEvent event) throws IOException {
//		HomeScreenHandler homeScreenHandler = new HomeScreenHandler(stage, ConfigsHOME);
//		homeScreenHandler.setScreenTitle("Home Screen");
//		homeScreenHandler.show();
		SingletonObject instance = SingletonObject.getInstance();
		BaseScreenHandler previousScreen = instance.getPreviousScreen();
		previousScreen.show();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		type.setCellValueFactory(new PropertyValueFactory<>("type"));
		weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
		cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
		
		tbListBike.setRowFactory(tv -> {
		    TableRow<BikeModel> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY && event.getClickCount() == 1) {
		        	BikeModel clickedRow = row.getItem();
		    		try {
		    			BaseScreenHandler bikeScreenHandler = new BikeScreenHandler(stage, Configs.BIKE, clickedRow);
		    			bikeScreenHandler.setScreenTitle("Bike Screen");
		    			bikeScreenHandler.show();
		    			SingletonObject.getInstance().addScreen(thisScreen);
		    		} catch (IOException e) {
		    			e.printStackTrace();
		    		}
		        }
		    });
		    return row ;
		});
	}
	
	private void setupInfomation() {
		
		BikeController bikeController = new BikeController();
		List<BikeModel> bikes = bikeController.findBikeByStationAndSearchInfomation(
				stationModel.getId(),
				SingletonObject.getInstance().getSearchQueryInfomation()
			);
		
		if(bikes != null) tbListBike.setItems(FXCollections.observableArrayList(bikes));
	}
}
