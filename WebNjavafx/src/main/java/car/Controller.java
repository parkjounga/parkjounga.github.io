package car;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map;

import org.apache.el.stream.Optional;
import org.vision.popol.fxhomebook.ConnectionFactory;
import org.vision.popol.fxhomebook.Homebook;
import org.vision.popol.fxhomebook.TableViewFactory;
import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.layout.BorderPane;

public class Controller implements Initializable {
	@FXML
    private Button about;
	
	
    @FXML
    private BorderPane bordPane;

    @FXML
    private TextField txtSerialno;

    @FXML
    private TextField txtcarnumber;

    @FXML
    private TextField txtcarname;

    @FXML
    private TextField txtcarcolor;

    @FXML
    private TextField txtcarmarker;

    @FXML
    private TextField txtcarprice;

    @FXML
    private Button btnInsert;

    @FXML
    private TableView<Map> tableView;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnFind;

    @FXML
    private Button btnSelectAll;

    @FXML
    private Button btnEnd;
    
    @FXML
    private TextArea textArea;

    @FXML
    private Button btnSql;

    @FXML
    void onDelete(ActionEvent event) {
    	Map<String, String> map = tableView.getSelectionModel().getSelectedItem();
		System.out.println(map);
    	CarDao dao= new CarDao();
    	try {	
    	dao.delete(ConnectionFactory.getConnection(), Long.parseLong(map.get("SERIALNO")));
		refreshTableview();
	} catch (NumberFormatException e) {
//TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
//TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
//TODO Auto-generated catch block
		e.printStackTrace();
	}
    	
    }

    @FXML
    void onEnd(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void onFind(ActionEvent event) {
    	CarDao dao=new CarDao();
    	
    	try {
			List<Car>data =dao.selectAll(ConnectionFactory.getConnection());
			tableView.getItems().clear();
			for(Car x:data) {
				Map<String,String> map =new HashMap<>();
				map.put("SERIALNO",x.getSerialno()+"");
				map.put("CARNUMBER",x.getCarnumber()+"");
				map.put("CARNAME",x.getCarname());
				map.put("CARCOLOR",x.getCarcolor());
				map.put ("CARMAKER",x.getCarmaker());
				map.put("CARPRICE",x.getCarprice()+"");
				
				tableView.getItems().add(map);
			}
			
    	
    	
    	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    
    }

    @FXML
    void onInsert(ActionEvent event) {
    	CarDao dao= new CarDao ();
    	Car vo = new Car();
    	//vo.setSerialno(Long.parseLong(txtSerialno.getText()));
		vo.setCarnumber(txtcarnumber.getText());
		vo.setCarname(txtcarname.getText());
		vo.setCarcolor(txtcarcolor.getText());
		vo.setCarmaker(txtcarmarker.getText());
		vo.setCarprice(txtcarprice.getText());

		try {
			dao.insert(ConnectionFactory.getConnection(), vo);
			refreshTableview();
		} catch (ClassNotFoundException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}


   	}


    public void refreshTableview() {
    	try {
			tableView = TableViewFactory.getTable("SELECT * FROM CAR", ConnectionFactory.getConnection());
			tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

				@Override
				public void changed(ObservableValue observable, Object oldValue, Object newValue) {
			    Map<String, String> rowData = tableView.getSelectionModel().getSelectedItem();
				txtSerialno.setText(rowData.get("SERIALNO"));
			    txtcarnumber.setText(rowData.get("CARNUMBER"));
			    txtcarname.setText(rowData.get("CARNAME"));
				txtcarcolor.setText(rowData.get("CARCOLOR"));
				txtcarmarker.setText(rowData.get("CARMARKER"));
				txtcarprice.setText(rowData.get("CARPRICE"));
			
					
				}
			});

			bordPane.setCenter(tableView);
					
				
    	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	refreshTableview();
		
	}
    
    @FXML
    void onSelectAll(ActionEvent event) {
    	refreshTableview();
    }
    
    @FXML
    void onSql(ActionEvent event) {
    	String sql = textArea.getText();
		sql = sql.replace(";", "");
		
		try {
			tableView = TableViewFactory.getTable(sql, ConnectionFactory.getConnection());
			bordPane.setCenter(tableView);
		
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void onUpdate(ActionEvent event) {
    	Car vo= new Car();
    	CarDao dao=new CarDao();
    	vo.setSerialno(Long.parseLong(txtSerialno.getText()));
    	vo.setCarnumber(txtcarnumber.getText());
        vo.setCarname(txtcarname.getText());
 	    vo.setCarcolor(txtcarcolor.getText());
 	    vo.setCarmaker(txtcarmarker.getText());
 	    vo.setCarprice(txtcarprice.getText());
 	   
 	  try {
		dao.update(ConnectionFactory.getConnection(), vo);
		refreshTableview();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    
    
    @FXML
    void aboutAction(ActionEvent event) {
            Alert alert =new Alert (AlertType.INFORMATION);
            alert.setTitle("알아보기");
            alert.setHeaderText("프로그램정보");
            alert.setContentText("효성비젼학원 ");
            alert.showAndWait();
    }

}
