package car;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CarMain extends Application {
	
	
	
	
 public static void main(String[] args) {
	  launch(args);
}

@Override
public void start(Stage primaryStage) throws Exception {
	Parent root =FXMLLoader.load(getClass().getResource("Car.fxml"));
	Scene scene =new Scene(root);
	
	primaryStage.setScene(scene);
	
	primaryStage.setTitle("자동차등록부") ;
	primaryStage.show();
	
}
}
