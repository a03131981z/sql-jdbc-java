package pos_java_jdbc.pos_java_jdbc;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.sun.jna.platform.win32.WinBase.PROCESS_INFORMATION.ByReference;

import dao.UserPosDAO;
import model.UserPosJava;

public class TesteBancoJDBC {

	@Test
	public void initBanco() {
		UserPosDAO userPosDAO = new UserPosDAO();
		UserPosJava userPosJava = new UserPosJava();
		userPosJava.setId(10L);
		userPosJava.setNome("Arthur");
		userPosJava.setEmail("arthur@gmail.com");
		
		userPosDAO.salvar(userPosJava);
	}
	
	public void initListar() throws SQLException {
		UserPosDAO dao = new UserPosDAO();
		List<UserPosJava> list = dao.listar();
		for(UserPosJava userPosJava: list) {
			System.out.println(userPosJava);
			System.out.println("-----------------------------------");
		}
	}
	
	@Test
	public void initBuscar() {
		UserPosDAO dao = new UserPosDAO();
		
		try {
			UserPosJava userPosJava = dao.buscar(10L);
			System.out.println(userPosJava);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initAtualizar() {
		try {
			UserPosDAO dao = new UserPosDAO();
			UserPosJava objetoBanco = dao.buscar(5L);
			objetoBanco.setNome("Nome mudado com método atualizar");
			dao.atualizar(objetoBanco);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
