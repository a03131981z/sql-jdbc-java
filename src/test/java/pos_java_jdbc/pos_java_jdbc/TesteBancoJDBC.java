package pos_java_jdbc.pos_java_jdbc;
import org.junit.Test;
import dao.UserPosDAO;
import model.UserPosJava;

public class TesteBancoJDBC {

	@Test
	public void initBanco() {
		UserPosDAO userPosDAO = new UserPosDAO();
		UserPosJava userPosJava = new UserPosJava();
		userPosJava.setId(6L);
		userPosJava.setNome("Arthur");
		userPosJava.setEmail("arthur@gmail.com");
		
		userPosDAO.salvar(userPosJava);
	}
}
