package views.screen.result;

import java.io.IOException;

import common.SingletonObject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;

public class ResultScreenHandler extends BaseScreenHandler {


	public ResultScreenHandler(Stage stage, String screenPath, String result, String message) throws IOException {
		super(stage, screenPath);
		resultLabel.setText(result);
		messageLabel.setText(message);
	}
	public ResultScreenHandler(Stage stage, String screenPath, String result, String message, String pageTitle) throws IOException {
		super(stage, screenPath);
		resultLabel.setText(result);
		messageLabel.setText(message);
		this.pageTitle.setText(pageTitle);
	}

	@FXML
	private Label pageTitle;

	@FXML
	private Label resultLabel;

	@FXML
	private Button okButton;
	
	@FXML
	private Label messageLabel;

	@FXML
	void confirmPayment(MouseEvent event) throws IOException {
		SingletonObject.getInstance().clearScreen();
		HomeScreenHandler homeScreenHandler = new HomeScreenHandler(stage, Configs.HOME);
		homeScreenHandler.setScreenTitle("Home Screen");
		homeScreenHandler.show();
	}

}
