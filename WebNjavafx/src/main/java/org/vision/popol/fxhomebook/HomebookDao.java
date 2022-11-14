package org.vision.popol.fxhomebook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HomebookDao implements IDao<Homebook, Long> {

	@Override
	public int insert(Connection conn, Homebook vo) throws SQLException {
		String sql = "INSERT INTO HOMEBOOK (SERIALNO,DAY,SECTION,ACCOUNTTITLE,REMARK,REVENUE,EXPENSE) "
		+ "VALUES(SEQ_HOMEBOOK.NEXTVAL,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//pstmt.setLong(1,vo.getSerialno());
		pstmt.setTimestamp(1, vo.getDay());
		pstmt.setString(2,vo.getSection());
		pstmt.setString(3,vo.getAccounttitle());
		pstmt.setString(4,vo.getRemark());
		pstmt.setInt(5,vo.getRevenue());
		pstmt.setInt(6,vo.getExpense());
		
		int res = 0;

		try {
		res = pstmt.executeUpdate();
		} catch (SQLException e) {
		//throw new SQLException("");
			return -1;
		}
		conn.close();
		return res;
		}

	@Override
	public int delete(Connection conn, Long pk){
      String sql= "DELETE HOMEBOOK WHERE SERIALNO=?";
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
	public int update(Connection conn, Homebook vo) throws SQLException {
		String sql = "UPDATE HOMEBOOK "
				 +"SET DAY=? , SECTION =? , ACCOUNTTITLE=? , "
				+ "REMARK=? , REVENUE=? , EXPENSE=?"
				 + "WHERE SERIALNO=?" ;
		int res=0;
		try {
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setTimestamp(1,vo.getDay() );
			pstmt.setString(2,vo.getSection() );
			pstmt.setString(3,vo.getAccounttitle() );
			pstmt.setString(4,vo.getRemark() );
			pstmt.setInt(5,vo.getRevenue() );
			pstmt.setInt(6,vo.getExpense() );
			pstmt.setLong(7,vo.getSerialno() );
			res=pstmt.executeUpdate();
		} catch (SQLException e) {
		 	return 0;
		}
		
		
		conn .close();	//
		return res;
	}

	@Override
	public Homebook select(Connection conn, Long pk) throws SQLException {
		String sql = "SELECT * FROM HOMEBOOK WHERE SERIALNO=?";
		PreparedStatement pstmt =conn.prepareStatement(sql);
		pstmt.setLong(1, pk);
		ResultSet rs = pstmt.executeQuery();
		Homebook vo =new Homebook();
		while(rs.next()) {
			vo.setSerialno(pk);
			vo.setDay(rs.getTimestamp("DAY"));
			vo.setSection(rs.getString("SECTION"));
			vo.setAccounttitle(rs.getString("ACCOUNTTITLE"));
			vo.setRemark(rs.getString("REMARK"));
			vo.setRevenue(rs.getInt("REVENUE"));
			vo.setExpense(rs.getInt("EXPENSE"));
		}
		 conn.close();
		 return vo;
	}

	@Override
	public List<Homebook> selectAll(Connection conn) throws SQLException {
		String sql ="SELECT * FROM HOMEBOOK" ; 
		//Statement stmt =  conn.createStatement();
		//ResultSet rs = stmt.executeQuery(sql);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<Homebook> data = new ArrayList<>();
		while(rs.next()) {
		Homebook vo = new Homebook();
		vo.setSerialno(rs.getLong("SERIALNO"));
		vo.setDay(rs.getTimestamp("DAY"));
		vo.setSection(rs.getString("SECTION"));
		vo.setAccounttitle(rs.getString("ACCOUNTTITLE"));
		vo.setRemark(rs.getString("REMARK"));
		vo.setRevenue(rs.getInt("REVENUE"));
		vo.setExpense(rs.getInt("EXPENSE"));
		data.add(vo);
		}
		
		conn.close();
		return data;
	}

	@Override
	public List<Homebook> selectByDay(Connection conn, Timestamp startDate, Timestamp endDate)
			throws SQLException { ///WHERE조건
		String sql ="SELECT * FROM HOMEBOOK WHERE DAY BETWEEN ? AND ?" ; 
		PreparedStatement pstmt =conn.prepareStatement(sql);
		pstmt.setTimestamp(1, startDate);
		pstmt.setTimestamp(2, endDate);
		ResultSet rs = pstmt.executeQuery();
		List<Homebook> data = new ArrayList<>();
		while(rs.next()) {
		Homebook vo = new Homebook();
		vo.setSerialno(rs.getLong("SERIALNO"));
		vo.setDay(rs.getTimestamp("DAY"));
		vo.setSection(rs.getString("SECTION"));
		vo.setAccounttitle(rs.getString("ACCOUNTTITLE"));
		vo.setRemark(rs.getString("REMARK"));
		vo.setRevenue(rs.getInt("REVENUE"));
		vo.setExpense(rs.getInt("EXPENSE"));
		data.add(vo);
		}
		return data;
	}
	 
	
	public long getSumRevenue(List<Homebook> data ) {
		   long hab =0L;
		   for(Homebook x :data) {
			   hab += x.getRevenue();
		   }
		   
		return hab;   
	}
	
	public long getSumExpense(List<Homebook> data ) {
		   long hab =0L;
		   for(Homebook x :data) {
			   hab += x.getExpense();
		   }
		   
		return hab;   
	}
	

}
