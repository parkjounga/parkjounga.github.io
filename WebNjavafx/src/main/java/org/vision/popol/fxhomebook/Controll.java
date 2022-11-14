package org.vision.popol.fxhomebook;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class Controll implements Initializable {
	
	
	@FXML
    private DatePicker startDay;

    @FXML
    private DatePicker endDay;
    
	@FXML
	private TextArea textArea;

	@FXML
	private Button btnSql;

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
	private BorderPane borderPane;

	@FXML
	private TextField txtSerialno;

	@FXML
	private TextField txtDay;

	@FXML
	private TextField txtSection;

	@FXML
	private TextField txtAccounttitle;

	@FXML
	private TextField txtRemark;

	@FXML
	private TextField txtRevenue;

	@FXML
	private TextField txtExpense;

	@FXML
	private Button btnInsert;

	@FXML
	private TableView<Map> tableView;

	@FXML
	private TextField txtRevenueHab;

	@FXML
	private TextField txtExpenseHab;

	@FXML
	void onInsert(ActionEvent event) {
// 자료 입력 부분
// dao를 사용할 수 있게 준비작업을 먼저
		HomebookDao dao = new HomebookDao();
		Homebook vo = new Homebook();
		//vo.setSerialno(Long.parseLong(txtSerialno.getText()));
		Timestamp timestamp = null;
		if (txtDay.getText().equals("")) {
			timestamp = new Timestamp(System.currentTimeMillis());
		} else {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d = df.parse(txtDay.getText());
				timestamp = new Timestamp(d.getTime());
			} catch (ParseException e) {
// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		vo.setDay(timestamp);
		vo.setSection(txtSection.getText());
		vo.setAccounttitle(txtAccounttitle.getText());
		vo.setRemark(txtRemark.getText());
		vo.setRevenue(Integer.parseInt(txtRevenue.getText()));
		vo.setExpense(Integer.parseInt(txtExpense.getText()));

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
			tableView = TableViewFactory.getTable("SELECT * FROM HOMEBOOK", ConnectionFactory.getConnection());
			tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
				

				@Override
				public void changed(ObservableValue observable, Object oldValue, Object newValue) {
					Map<String, String> rowData = tableView.getSelectionModel().getSelectedItem();
					txtSerialno.setText(rowData.get("SERIALNO"));
					txtDay.setText(rowData.get("DAY"));
					txtSection.setText(rowData.get("SECTION"));
					txtAccounttitle.setText(rowData.get("ACCOUNTTITLE"));
					txtRemark.setText(rowData.get("REMARK"));
					txtRevenue.setText(rowData.get("REVENUE"));
					txtExpense.setText(rowData.get("EXPENSE"));
					
				}
			});

			borderPane.setCenter(tableView);
// 집계 작업
			HomebookDao dao = new HomebookDao();
			List<Homebook> data = dao.selectAll(ConnectionFactory.getConnection());
			long rh = dao.getSumRevenue(data);
			long eh = dao.getSumExpense(data);
			txtRevenueHab.setText(rh + "");
			txtExpenseHab.setText(eh + "");

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
// TODO 컨트롤스(컴포넌트)들의 초기화 작업
// 가계부 디비의 모든자료를 읽어 tableView를 만들어 붙이는 작업
		refreshTableview();
	}

	@FXML
	void onDelete(ActionEvent event) {
// TODO - 선택한 행을 인지하고 그행의 시리얼번호를 읽어 DAO의 delete메소드를 호출 하고
// 반영된 내용을 화면에 출력한다. //소프트웨어개발격언 - divide & qunqur !
// int selectRow = tableView.getSelectionModel().getSelectedIndex();
		Map<String, String> map = tableView.getSelectionModel().getSelectedItem();
		System.out.println(map);
		HomebookDao dao = new HomebookDao();
		try {
// db삭제 삭제작업
			dao.delete(ConnectionFactory.getConnection(), Long.parseLong(map.get("SERIALNO")));
			refreshTableview();
		} catch (NumberFormatException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void onEnd(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void onFind(ActionEvent event) {
	  System.out.println(startDay.getValue());
      System.out.println(endDay.getValue());
      HomebookDao dao= new HomebookDao();
      String[] a=null;
	  String[] b=null;
	try {
		  a = startDay.getValue().toString().split("-");
		  b = endDay.getValue().toString().split("-");
	} catch (Exception e1) {
		
		JOptionPane.showMessageDialog(null, "날짜 정보가 없어 색인하지 못합니다");
	}
      int yearA =  Integer.parseInt(a[0])-1990;
      int yearB =   Integer.parseInt(b[0])-1990;
      int MonthA = Integer.parseInt(a[1])-1;
      int MonthB =  Integer.parseInt(b[1])-1;
      int dayA = Integer.parseInt(a[2]);
      int dayB = Integer.parseInt(b[2]);
      
      
      Timestamp ta = new Timestamp(yearA,MonthA,dayA,0,0,0,0);
      Timestamp tb = new Timestamp(yearB,MonthB,dayB,0,0,0,0);
      try {
		List<Homebook>data =
				  dao.selectByDay(ConnectionFactory.getConnection(), ta,tb);
		          
		tableView.getItems().clear();
		// tableView에있는 아이템들 제거
		for(Homebook x:data) {
			Map<String,String> map =new HashMap<>();
			map.put("SERIALNO",x.getSerialno()+"");
			map.put("DAY",x.getDay()+"");
			map.put("SECTION",x.getSection());
			map.put("ACCOUNTTITLE",x.getAccounttitle());
			map.put ("REMARK",x.getRemark());
			map.put("REVENUE",x.getRevenue()+"");
			map.put("EXPENSE",x.getExpense()+"");
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
	void onSelectAll(ActionEvent event) {
		refreshTableview();
	}

	@FXML
	void onUpdate(ActionEvent event) {
// TODO - 입력 텍스트필드들의 정보로 Homebook vo를 만든 다음 dao의 update()메소드를 호출하고
// 화면을 갱신한다.
		Homebook vo = new Homebook();
		vo.setSerialno(Long.parseLong(txtSerialno.getText()));
// 2022-07-12 10:29:37
		String[] str = txtDay.getText().split("-| |:");
		System.out.println(Arrays.toString(str));
		int year = Integer.parseInt(str[0]) - 1900;
		int month = Integer.parseInt(str[1]) - 1;
		int day = Integer.parseInt(str[2]);
		int h = Integer.parseInt(str[3]);
		int m = Integer.parseInt(str[4]);
		int s = Integer.parseInt(str[5]);
		int ms = 0;
		vo.setDay(new Timestamp(year, month, day, h, m, s, ms));
		vo.setSection(txtSection.getText());
		vo.setAccounttitle(txtAccounttitle.getText());
		vo.setRemark(txtRemark.getText());
		vo.setRevenue(Integer.parseInt(txtRevenue.getText()));
		vo.setExpense(Integer.parseInt(txtExpense.getText()));
		HomebookDao dao = new HomebookDao();
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
	void onSql(ActionEvent event) {
		String sql = textArea.getText();
		sql = sql.replace(";", "");
		try {
			tableView = TableViewFactory.getTable(sql, ConnectionFactory.getConnection());
			borderPane.setCenter(tableView);
		} catch (ClassNotFoundException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}