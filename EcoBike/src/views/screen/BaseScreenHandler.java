package views.screen;


import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.home.HomeScreenHandler;

import java.io.IOException;

public class BaseScreenHandler extends FXMLScreenHandler{

    private Scene scene;
    private BaseScreenHandler prev;
    protected final Stage stage;
    protected HomeScreenHandler homeScreenHandler;

    public BaseScreenHandler(String screenPath) throws IOException {
        super(screenPath);
        this.stage = new Stage();
        homeScreenHandler = new HomeScreenHandler(stage, Configs.HOME);
    }

    public BaseScreenHandler(Stage stage, String screenPath) throws IOException {
        super(screenPath);
        this.stage = stage;
    }

    public void show() {
        if (this.scene == null) {
            this.scene = new Scene(this.content);
        }
        this.scene.getStylesheets().add(getClass().getResource(Configs.CSS_STYLE).toExternalForm());
        this.stage.setScene(this.scene);
        this.stage.centerOnScreen();
        this.stage.show();
    }

    public void setScreenTitle(String title) {
        this.stage.setTitle(title);
    }

    public void setPreviousScreen(BaseScreenHandler prev) {
        this.prev = prev;
    }

    public BaseScreenHandler getPreviousScreen() {
        return this.prev;
    }
    public void setHomeScreenHandler(HomeScreenHandler HomeScreenHandler) {
		this.homeScreenHandler = HomeScreenHandler;
	}
    
    public BaseScreenHandler getHomeSCreenHandler() {
    	return this.homeScreenHandler;
    }
}
