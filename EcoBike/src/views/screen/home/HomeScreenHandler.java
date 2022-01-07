package views.screen.home;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import common.SearchQueryInfomation;
import common.SingletonObject;
import common.exception.PriceRangeException;
import controller.StationController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.StationModel;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.bike.ReturnBikeScreenHandler;
import views.screen.station.AddStationScreenHandler;

public class HomeScreenHandler extends BaseScreenHandler implements Initializable {
	@FXML
	private TextField searchQuery;
	
	@FXML
	private TextField minPrice;
	
	@FXML
	private TextField maxPrice;
	
	@FXML
	private ComboBox<String> typeBike;
	
	@FXML
	private ListView<StationModel> listStation; 
	
	@FXML
	private Button searchButton;

	private StationController stationController;
	
	public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
	}
	
	private BaseScreenHandler thisScreen = this;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		stationController = new StationController();
		List<StationModel> stations = stationController.findAllAvailable();
//		System.out.println(stations);
		listStation.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		if(stations!= null) listStation.getItems().addAll(stations);
		
		listStation.setCellFactory(param -> new ListCell<StationModel>() {
            {
                setFont(new Font(15));
            }
            @Override
            protected void updateItem(StationModel item, boolean empty) {
                super.updateItem(item, empty);

                if (item != null && !empty) {
                    setText(item.getShortInfomation());
                    this.setOnMouseClicked(new HomeMouseClickHandler(thisScreen, item, stage));
                } else {
                    setText(null);
                }
            }
        });
	}
	
	private void validateRangePrice(String minPriceStr, String maxPriceStr) 
			throws NumberFormatException, PriceRangeException{
		Integer minPriceInt = minPriceStr.equals("") ? 0 : Integer.parseInt(minPriceStr);
		Integer maxPriceInt = maxPriceStr.equals("") ? 0 : Integer.parseInt(maxPriceStr);
		
		if(minPriceInt < 0 || maxPriceInt < 0) throw new PriceRangeException("Max price or min price not less than 0.");
				
		if(maxPriceStr.equals("")) return;
			
		if(maxPriceInt < minPriceInt) throw new PriceRangeException("Max price have not less than min price");
	}
}
