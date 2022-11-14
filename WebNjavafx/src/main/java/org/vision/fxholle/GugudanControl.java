package org.vision.fxholle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GugudanControl {

    @FXML
    private TextField txtDan;

    @FXML
    private Button dtndispGugudan;

    @FXML
    private TextArea TextArea;

    @FXML
    void displayGugudan(ActionEvent event) {
    // 구구단출력하는소스
      int 단 =Integer.parseInt(txtDan.getText());
      for (int i=1;i<=9;i++) {
    	  TextArea.appendText(단+"*"+i+"="+(단*i)+"\n");
      }
    
    
    }

}
