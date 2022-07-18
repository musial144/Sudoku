package pl.kompo;

import javafx.application.Application;
import javafx.stage.Stage;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import pl.kompo.Controller;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.initialize(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
