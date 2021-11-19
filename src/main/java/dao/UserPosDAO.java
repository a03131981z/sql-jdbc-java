package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.postgresql.util.PSQLException;

import java.util.ArrayList;
import conexao_jdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.UserPosJava;

public class UserPosDAO {

	private  Connection connection;
	
	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(UserPosJava userPosJava) {
		try {
			String sql = "insert into userposjava (nome, email) values (?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userPosJava.getNome());
			insert.setString(2, userPosJava.getEmail());
			insert.execute();
			connection.commit(); //Salva no banco
		
		}catch (Exception e) {
			try {
				connection.rollback(); //Reverte operação
			}catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}
	}
	
	public void salvarTelefone(Telefone telefone) {
		
		try {
			String sql = "insert into telefoneuser(numero, tipo, usuariopessoa) values(?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getUsuario());
			statement.execute();
			connection.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			
			try {
				connection.rollback();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public List<UserPosJava> listar() throws SQLException{
		List<UserPosJava> list = new ArrayList<UserPosJava>();
		String sql = "select * from userposjava";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			UserPosJava userPosJava = new UserPosJava();
			userPosJava.setId(resultado.getLong("id"));
			userPosJava.setNome(resultado.getString("nome"));
			userPosJava.setEmail(resultado.getString("email"));
			
			list.add(userPosJava);
		}
		
		return list;
	}
	
	public UserPosJava buscar(Long id) throws SQLException{
		UserPosJava retorno = new UserPosJava();
		
		String sql = "select * from userposjava where id = "+id;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) { //Retorna apenas um ou nenhum
			
			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));
				
		}	
		return retorno;
	}
	
	public List<BeanUserFone> listaUserFone(Long idUser){
		List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();
		String sql = "select nome, numero, email from telefoneuser as fone";
		sql += " inner join userposjava as userp ";
		sql += " on fone.usuariopessoa = userp.id ";
		sql += "where userp.id = "+idUser;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				BeanUserFone userFone = new BeanUserFone();
				userFone.setEmail(resultSet.getString("email"));
				userFone.setNome(resultSet.getString("nome"));
				userFone.setNumero(resultSet.getString("numero"));
				beanUserFones.add(userFone);	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return beanUserFones;
	}
	
	public void atualizar(UserPosJava userPosJava) {
		try {
			
			String sql = "update userposjava set nome = ? where id = "+userPosJava.getId();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userPosJava.getNome());
			statement.execute();
			connection.commit();
		
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void deletar(Long id) {
		try {
			String sql = "delete from userposjava where id = "+id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();

		}catch(SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
