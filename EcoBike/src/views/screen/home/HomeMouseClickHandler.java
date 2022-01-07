package views.screen.home;

import java.io.IOException;

import common.SingletonObject;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.StationModel;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.station.StationDetailScreenHandler;

public class HomeMouseClickHandler implements EventHandler<MouseEvent>{
	
	private StationModel item;
	
	private Stage stage;
	
	private BaseScreenHandler screen;

	public HomeMouseClickHandler(BaseScreenHandler screen, StationModel item, Stage stage) {
		super();
		this.item = item;
		this.stage = stage;
		this.screen = screen;
	}

	@Override
	public void handle(MouseEvent event) {
		StationDetailScreenHandler stationDetailScreenHandler;
		try {
			stationDetailScreenHandler = new StationDetailScreenHandler(stage, Configs.STATION_DETAIL, item);
			stationDetailScreenHandler.setScreenTitle("Station Detail");
			stationDetailScreenHandler.show();
			SingletonObject.getInstance().addScreen(screen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
