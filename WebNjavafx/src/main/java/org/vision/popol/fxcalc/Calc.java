package org.vision.popol.fxcalc;

import javax.naming.spi.DirStateFactory.Result;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class Calc {

    @FXML
    private TextField txt1;

    @FXML
    private TextField txt2;

    @FXML
    private Button add;

    @FXML
    private Button minus;

    @FXML
    private Button calcx;

    @FXML
    private Button divide;

    @FXML
    private TextField TextArea;

    @FXML
    void displayadd(ActionEvent event) {

      double a= Double.parseDouble(txt1.getText());
      double b= Double.parseDouble(txt2.getText());
      double res= a+b;
      TextArea.setText(res+"");
    }

    @FXML
    void displaydivide(ActionEvent event) {
    	double a= Double.parseDouble(txt1.getText());
        double b= Double.parseDouble(txt2.getText());
        double res= a/b;
        TextArea.setText(res+"");
    }

    @FXML
    void displayminus(ActionEvent event) {
    	double a= Double.parseDouble(txt1.getText());
        double b= Double.parseDouble(txt2.getText());
        double res= a-b;
        TextArea.setText(res+"");
    }

    @FXML
    void displayx(ActionEvent event) {
    	double a= Double.parseDouble(txt1.getText());
        double b= Double.parseDouble(txt2.getText());
        double res= a*b;
        TextArea.setText(res+"");
    }

   
}
