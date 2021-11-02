package dao;
import java.sql.Connection;
import conexao_jdbc.SingleConnection;

public class UserPosDAO {

	private  Connection connection;
	
	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}
}
