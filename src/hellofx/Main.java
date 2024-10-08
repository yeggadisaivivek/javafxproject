package hellofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("test1.fxml"));
    	Controller dashboard = Controller.getInstance();
        
        
        loader.setController(dashboard);
        
    	Parent root = loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("test1.fxml"));
        primaryStage.setTitle("Farm Dashboard");
        primaryStage.setScene(new Scene(root));
        
        
        
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}