package car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class CarDao implements IDao <Car,Long> {

	@Override
	public int insert(Connection conn, Car vo) throws SQLException {
		String sql = "INSERT INTO CAR (SERIALNO,CARNUMBER,CARNAME,CARCOLOR,CARMAKER,CARPRICE) "
				+ "VALUES(SEQ_CAR.NEXTVAL,?,?,?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				//pstmt.setLong(1,vo.getSerialno());
				pstmt.setString(1,vo.getCarnumber());
				pstmt.setString(2,vo.getCarname());
				pstmt.setString(3,vo.getCarcolor());
				pstmt.setString(4,vo.getCarmaker());
				pstmt.setString(5,vo.getCarprice() );
				int res = 0;
				
				try {
					res = pstmt.executeUpdate();
					} catch (SQLException e) {
					return -1;
					}
					conn.close();
					return res;
					}

	@Override
	public int delete(Connection conn, Long pk) throws SQLException {
		String sql= "DELETE CAR WHERE SERIALNO=?";
	      int res =0;
	      
	      try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1,pk);
			res =pstmt.executeUpdate();
		} catch (SQLException e) {
			return -1;
			
		}
	      
		return res;
	}

	@Override
	public int update(Connection conn, Car vo) throws SQLException {
		String sql = "UPDATE CAR " 
				 +"SET CARNUMBER=? , CARNAME =? , CARCOLOR=? , "
				+ "CARMAKER=? , CARPRICE=?"
				 + "WHERE SERIALNO=?" ;
		int res=0;
		try {
			PreparedStatement pstmt =conn.prepareStatement(sql);
			
			pstmt.setString(1,vo.getCarnumber() );
			pstmt.setString(2,vo.getCarname() );
			pstmt.setString(3,vo.getCarcolor() );
			pstmt.setString(4,vo.getCarmaker() );
			pstmt.setString(5,vo.getCarprice() );
			pstmt.setLong(6,vo.getSerialno() );
			res=pstmt.executeUpdate();
		} catch (SQLException e) {
		 	return 0;
		}
		
		
		conn .close();	
		return res;
	}
		


	
	@Override
	public List<Car> selectAll(Connection conn) throws SQLException {
		String sql ="SELECT * FROM CAR" ; 
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<Car> data = new ArrayList<>();
		while(rs.next()) {
			Car vo =new Car();
			vo.setSerialno(rs.getLong("SERIALNO"));
			vo.setCarnumber(rs.getString("CARNUMBER"));
			vo.setCarname(rs.getString("CARNAME"));
			vo.setCarcolor(rs.getString("CARCOLOR"));
			vo.setCarmaker(rs.getString("CARMAKER"));
			vo.setCarprice(rs.getString("CARPRICE"));
			data.add(vo);
		}
		conn.close();
		return data;
	}

	@Override
	public List<Car> selectByDay(Connection conn, Timestamp startDate, Timestamp startendDate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}