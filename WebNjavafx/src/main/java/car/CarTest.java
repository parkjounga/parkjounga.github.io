package car;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.vision.popol.fxhomebook.ConnectionFactory;


public class CarTest {
CarDao dao = new CarDao();
	
	
	public static void main(String[] args)throws SQLException, ClassNotFoundException  {
		CarTest x = new CarTest ();
		x.insert();
	    //x.delete();
	//x.select();
		//x.업데이트성공테스트();
		//x.selectAll();
	}

	
	public void insert() {
		
	Connection conn = null;
	
	try {
		conn = ConnectionFactory.getConnection();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	Car vo = new Car (); 
	//vo.setSerialno(1L);
	vo.setCarnumber("11나 4444");
	vo.setCarname("쏘렌토");
	vo.setCarcolor("red");
	vo.setCarmaker("기아");
	vo.setCarprice("4000");
	
	try {
		int res=dao.insert(conn, vo);
		if (res>0) {
			System.out.println("자료입력 성공~");
		}else {
			System.out.println("자료입력실패!");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public void 업데이트성공테스트() throws ClassNotFoundException, SQLException {
		Connection conn= null;
		conn = ConnectionFactory.getConnection();
		
		Car vo= new Car();
		vo.setSerialno(1L);
		vo.setCarnumber("11나 1111");
		vo.setCarname("투싼");
		vo.setCarcolor("black");
		vo.setCarmaker("현대");
		vo.setCarprice("2800");
		int res=dao.update(conn,vo);
		if (res==1) {
			System.out.println("업데이트테스트성공");
		}else {
			System.out.println("업데이트테스트실패");
		}
		}
	
	
	public void selectAll()throws ClassNotFoundException, SQLException {
		Connection conn = null;
		conn = ConnectionFactory.getConnection();
		List<Car>data =dao.selectAll(conn);
		for(Car x:data) {
			System.out.println(x);
		}
	}
	
	}
	
		
	
	
	
	
	

