package org.vision.popol.fxhomebook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// JDBC 1,2,4 단계를 전담하는 클래스 
public class ConnectionFactory {
private static Connection conn;
private static String url;
private static String user;
private static String pwd;
// 초기화 블럭 
static{
url = "jdbc:oracle:thin:@localhost:1521:xe";
user = "madang";
pwd ="madang";
}
// 위 초기화 블럭에서 지정한 디폴트한 값 이외의 정보로 세팅시키고자 할때에 사용함 
public static void setInfo(String url,String user,String pwd) {
ConnectionFactory.url =url;
ConnectionFactory.user =user;
ConnectionFactory.pwd =pwd;
}

public static Connection getConnection() throws SQLException,ClassNotFoundException{
// throws 와 throw의 차이점을 정리하세요.
try {
// 1단계 
Class.forName("oracle.jdbc.driver.OracleDriver"); // ClassNotFoundException throws
// 2단계 
conn = DriverManager.getConnection(url,user,pwd);// SQLException throws 
} catch (ClassNotFoundException e) {
throw new ClassNotFoundException("오라클 드라이버가 없습니다. ㅠㅠ");
} catch (SQLException e) {
throw new SQLException("url,user,pwd 잘못으로 Connection객체를 만들지 못했습니다!ㅠㅠ");
}
return conn;
}

public static void close() {
try {
if(conn!=null && !conn.isClosed()) conn.close();
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}
}