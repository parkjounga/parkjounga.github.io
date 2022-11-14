package car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

public class UsersDao implements UDao<Users,String>{

	@Override
	public int insert(Connection conn, Users vo) throws SQLException {
		String sql ="INSERT INTO USERS(ID,PW,NAME,PHONE) VALUES(SEQ_USERS.NEXTVAL,?,?,?,?) ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,vo.getId());
		pstmt.setString(2,vo.getPw());
		pstmt.setString(3,vo.getName());
		pstmt.setString(4,vo.getPhone());
		int res=0;
		try {
			res = pstmt.executeUpdate();
			} catch (SQLException e) {
			return -1;
			}
			conn.close();
			return res;
	}
	
	
	

	@Override
	public boolean select(Connection conn, String key) throws SQLException {

		boolean check = false;
		ResultSet rs = null;
     	String sql = "SELECT * FROM USERS WHERE ID = ? AND PW = ?";
		PreparedStatement pstmt =conn.prepareStatement(sql);
		pstmt.setString(1, key);
		pstmt.setString(2, key);
		rs = pstmt.executeQuery();
		check = rs.next();
		
		pstmt.close();
		conn.close();
		return check;
	}


	@Override
	public List<Users> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Connection conn, Users vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Connection conn, String key) throws SQLException {
		return false;
		
	}
	}