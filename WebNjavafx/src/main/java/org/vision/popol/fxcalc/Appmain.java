package org.vision.popol.fxcalc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Appmain extends Application {

	public static void main(String[] args) {
		launch();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		//주요장면 명석 (최상위에깔려있는명석)
		Parent root =FXMLLoader.load(getClass().getResource("Rotot.fxml"));
		// gui장면
		Scene scene =new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("계산기");
		primaryStage.show();
		
	}


}
