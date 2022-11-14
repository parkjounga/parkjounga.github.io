package org.vision.popol.fxhomebook;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;



public class TestOfHomebookDao {

	HomebookDao dao = new HomebookDao();
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		TestOfHomebookDao x = new TestOfHomebookDao();
		
		
		
	}
	

	public void 지정한날짜의자료만가져오기테스트() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		conn = ConnectionFactory.getConnection();
		Timestamp a = new Timestamp(System.currentTimeMillis());
		Timestamp b = new Timestamp(System.currentTimeMillis());
		a.setYear(122);
		a.setMonth(6);
		a.setDate(1);

		b.setYear(122);
		b.setMonth(6);
		b.setDate(14);

		System.out.println(a);
		System.out.println(b);
		List<Homebook> data = dao.selectByDay(conn, a, b);
		for(Homebook x:data) {
		System.out.println(x);
		}

		}
	
	public void 자료선택해오기테스트() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		conn = ConnectionFactory.getConnection();
		Homebook vo = dao.select(conn, 2L);
		System.out.println(vo);

		}
	
	public void 자료수정성공여부테스트() throws ClassNotFoundException,SQLException{
		//매게변수는없어야한아 
		//테스트메소드명은띄어쓰기 놉 
	Connection conn= null;
	conn = ConnectionFactory.getConnection();
	
	Homebook vo =new Homebook();
	vo.setSerialno(1L);
	Timestamp day =new Timestamp(System.currentTimeMillis());
	day.setYear(122);day.setDate(12);
	day.setMonth(6); //7월을의미한다 , 로컬데이터는 그냥 7을써야7이된다 

	
	vo.setDay(day);
	vo.setSection("수입");
	vo.setAccounttitle("피복비");
	vo.setRemark("이번달월급수령");
	vo.setRevenue(8_000_000);
	vo.setExpense(0);
	int res=dao.update(conn,vo);
	if (res==1) {
		System.out.println("테스트성공");
	}else {
		System.out.println("테스트실패");
	}
	}
	
	
	
	
	public void 자료수정실패여부테스트() throws ClassNotFoundException,SQLException{
		//매게변수는없어야한아 
		//테스트메소드명은띄어쓰기 놉 
	Connection conn= null;
	conn = ConnectionFactory.getConnection();
	
	Homebook vo =new Homebook();
	vo.setSerialno(9999L);
	Timestamp day =new Timestamp(System.currentTimeMillis());
	day.setYear(2022);day.setDate(12);
	day.setMonth(6); //7월을의미한다 , 로컬데이터는 그냥 7을써야7이된다 

	
	vo.setDay(day);
	vo.setSection("수입");
	vo.setAccounttitle("피복비");
	vo.setRemark("이번달월급수령");
	vo.setRevenue(8_000_000);
	vo.setExpense(0);
	int res=dao.update(conn,vo);
	if (res==0) {
		System.out.println("수정실패성공");
	}else {
		System.out.println("수정실패실패");
	}
	}

	public void 삭제성공테스트 ()throws ClassNotFoundException, SQLException  {
		Connection conn = null;
		conn = ConnectionFactory.getConnection();
		int res =dao.delete(conn, 1L);
		if(res==1) {
			System.out.println("자료를 잘지웠습니다");
		}else {
			System.out.println("자료를 못 지웠습니다");
		}
		
		}
	
		//없는 시리얼로 호출
	public void 삭제실패테스트 ()throws ClassNotFoundException, SQLException  {
		Connection conn = null;
		conn = ConnectionFactory.getConnection();
		int res =dao.delete(conn, 1L);
		System.out.println(res);
		if(res==0) {
			System.out.println("정상적인 삭제 실패");
		}else {
			System.out.println("뭔가이상함");
		}
	}
	
	
	
	
	
	public void 모든자료읽어오기테스트() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		conn = ConnectionFactory.getConnection();
		List<Homebook> data = dao.selectAll(conn);
		for (Homebook x : data) {
			System.out.println(x);
		}
	}

///test case
	// test case
	public void 홈북다오의인서트기능이성공하는지테스트() {
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
		Homebook vo = new Homebook();
		vo.setSerialno(2L);
		Timestamp d = new Timestamp(System.currentTimeMillis());
		vo.setDay(d);
		vo.setSection("지출");
		vo.setAccounttitle("피복비");
		vo.setRemark("딸 여름용 물놀이 원피스를 사주다.");
		vo.setRevenue(0);
		vo.setExpense(7800);
		try {
			int res = dao.insert(conn, vo);
			if (res > 0)
				System.out.println("자료입력 성공!");
			else
				System.out.println("자료입력 실패!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// test case
	public void 홈북다오의인서트기능이실패하는지테스트() {
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
		Homebook vo = new Homebook();
		vo.setSerialno(1);
		Timestamp d = new Timestamp(System.currentTimeMillis());
		vo.setDay(d);
		vo.setSection("지출");
		vo.setAccounttitle("피복비");
		vo.setRemark("딸 여름용 물놀이 원피스");
		vo.setRevenue(0);
		vo.setExpense(7800);

		try {
			int res = dao.insert(conn, vo);
			if (res > 0)
				System.out.println("중복자료입력-입력에러!!!");
			else
				System.out.println("입력실패 테스트 성공!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
