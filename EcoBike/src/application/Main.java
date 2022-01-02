package application;
import java.text.ParseException;
import java.util.List;

import configs.Path;
import controller.BikeController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.BikeModel;
import views.screen.BaseScreenHandler;
import views.screen.bike.BikeScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.station.AddStationScreenHandler;
public class Main extends Application{

	@Override
	public void start(Stage primaryStage) {
		try {
//			Parent root = FXMLLoader.load(getClass().getResource("/views/fxml/test.fxml"));
//			primaryStage.setTitle("My Application");
//			primaryStage.setScene(new Scene(root));
//			primaryStage.show();

//			HomeScreenHandler homeScreenHandler = new HomeScreenHandler(primaryStage, Path.HOME);
//			homeScreenHandler.setScreenTitle("Home Screen");
//			homeScreenHandler.show();
			BikeController bikeController = new BikeController();
			List<BikeModel> res = bikeController.getAllBike();

			BaseScreenHandler bikeScreenHandler = new BikeScreenHandler(primaryStage, Path.BIKE, res.get(0));
			bikeScreenHandler.setScreenTitle("Bike Screen");
			bikeScreenHandler.show();
			
//			AddStationScreenHandler addStationScreenHandler = new AddStationScreenHandler(primaryStage, Path.ADD_STATION);
//			addStationScreenHandler.setScreenTitle("Add Station");
//			addStationScreenHandler.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ParseException {
		launch(args);
	}
}
