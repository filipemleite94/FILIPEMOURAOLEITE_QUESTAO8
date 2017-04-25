package teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.*;

public class Sprint1 {
	Sistema _system;

	@Before
	public void setUp() throws Exception {
		_system = new Sistema();
	}

	@Test
	public void testPegarLivroDesconhecido(){
		assertEquals(null, _system.pegarLivro("Livro"));
	}
	
	@Test
	public void testInserirUsuario(){
		Usuario user;
		user = new Usuario();
		assertEquals(false, _system.inserirUsuario(user));
		_system.logInMaster("");
		assertEquals(true, _system.inserirUsuario(user));
	}
	
	@Test
	public void testRemoverUsuario(){
		Usuario user, user2;
		user = new Usuario();
		user2 = new Usuario();
		_system.logInMaster("");
		_system.inserirUsuario(user);
		_system.inserirUsuario(user2);
		assertEquals(true, _system.removerUsuario(user));
		_system.logOffMaster();
		assertEquals(false, _system.removerUsuario(user2));
	}
	
	@Test
	public void testBloquearUsuario(){
		Usuario user, user2;
		user = new Usuario();
		user2 = new Usuario();
		assertEquals(Integer.MAX_VALUE, _system.bloquearUsuario(new Usuario(), 1));
		_system.logInMaster("");
		_system.inserirUsuario(user);
		_system.inserirUsuario(user2);
		assertEquals(5, _system.bloquearUsuario(user, 5));
		_system.logOffMaster();
		assertEquals(0, _system.bloquearUsuario(user2, 0));
	}
}
