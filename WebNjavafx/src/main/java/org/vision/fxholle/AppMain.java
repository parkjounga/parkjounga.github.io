package org.vision.fxholle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMain extends Application {

	public static void main(String[] args) {
		launch();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		//주요장면 명석 (최상위에깔려있는명석)
		Parent root =FXMLLoader.load(getClass().getResource("root.fxml"));
		// gui장면
		Scene scene =new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("구구단프로그램");
		primaryStage.show();
		
	}

}
