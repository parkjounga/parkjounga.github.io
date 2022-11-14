package car;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.vision.popol.fxhomebook.ConnectionFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class userscontroller{

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtpw;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtphone;

    @FXML
    private Button insert;

    @FXML
    void onInsert(ActionEvent event) {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Car.fxml"));
    	try {
			Parent root = (Parent) loader.load();
			Stage stage = new Stage();
			stage.setTitle("Car");
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    }





