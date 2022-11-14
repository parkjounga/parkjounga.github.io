package car;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UDao<V,K>  {
	public int insert(Connection conn,V vo) throws SQLException;	
	public boolean select(Connection conn,K key) throws SQLException;		
	public List<V> selectAll() throws SQLException;	
	public boolean update(Connection conn,V vo) throws SQLException;	
	public boolean delete(Connection conn,K key) throws SQLException;	
}
