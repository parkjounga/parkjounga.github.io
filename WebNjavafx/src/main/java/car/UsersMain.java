package car;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UsersMain extends Application {
public static void main(String[] args) {
	launch(args);
}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent root =FXMLLoader.load(getClass().getResource("Users.fxml"));
		Scene scene =new Scene(root);
		primaryStage.hide();	
		primaryStage.setScene(scene);
		
		primaryStage.setTitle("회원가입") ;
		primaryStage.show();
		
	}
	}
