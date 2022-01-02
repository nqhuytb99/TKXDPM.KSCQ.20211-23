package views.screen.station;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import controller.StationController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.StationModel;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.result.ResultScreenHandler;

public class AddStationScreenHandler extends BaseScreenHandler implements Initializable {
	public AddStationScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
	}
	@FXML
	private TextField tfName;
	@FXML
	private TextField tfAddress;
	@FXML
	private TextField tfNumBike;
	@FXML
	private TextField tfNumEbike;
	@FXML
	private TextField tfNumTBike;
	@FXML
	private TextField tfNumEmpty;
	@FXML
	private Button btnBack;
	@FXML
	private Button btnConfirm;

	// Event Listener on Button[#btnBack].onMouseClicked
	@FXML
	public void onBack(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnConfirm].onMouseClicked
	@FXML
	public void onConfirm(MouseEvent event) throws IOException {
		// TODO Autogenerated
		// add new station
		StationModel station = new StationModel();
		station.setName(tfName.getText());
		station.setAddress(tfAddress.getText());
		station.setNum_of_bikes(Integer.parseInt(tfNumBike.getText()));
		station.setNum_of_ebikes(Integer.parseInt(tfNumEbike.getText()));
		station.setNum_of_twinbikes(Integer.parseInt(tfNumTBike.getText()));
		station.setNum_of_empty_docks(Integer.parseInt(tfNumEmpty.getText()));
		StationController stationController = new StationController();
		int res = stationController.saveStation(station);
		if(res >=0) {
			BaseScreenHandler resultScreen = new ResultScreenHandler(this.stage, Configs.RESULT_SCREEN_PATH, "ADD STATION RESULT", "ADD SUCCESSFULLY", "ADD STATION" );
			resultScreen.setPreviousScreen(this);
			resultScreen.setScreenTitle("Result Screen");
			resultScreen.show();
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
