package teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.*;

public class Sprint2 {
	Sistema _system;

	@Before
	public void setUp() throws Exception {
		_system = new Sistema();
	}
	
	@Test
	public void testRegistrarEmprestimo(){
		Usuario user, user2;
		Livro livro;
		user = new Usuario();
		user2 = new Usuario();
		livro = new Livro("Teste");
		assertEquals(false, _system.registrarEmprestimoDeLivro(user, livro, 5));
		_system.logInMaster("");
		_system.inserirUsuario(user);
		_system.inserirUsuario(user2);
		assertEquals(true, _system.registrarEmprestimoDeLivro(user, livro, 5));
		_system.logOffMaster();
		_system.logInUsuario(user2);
		assertEquals(null, _system.pegarLivro(livro.getNome()));
		_system.logOffUsuario();
		_system.logInUsuario(user);
		assertEquals(null, _system.pegarLivro("Livro 2"));
	}
	
	@Test
	public void testRegistrarDevolucao(){
		Usuario user;
		Livro livro;
		user = new Usuario();
		livro = new Livro("Teste");
		_system.logInMaster("");
		_system.inserirUsuario(user);
		_system.registrarEmprestimoDeLivro(user, livro, 5);
		_system.logOffMaster();
		assertEquals(false, _system.registrarDevolucaoDeLivro(user, livro));
		_system.logInMaster("");
		assertEquals(true, _system.registrarDevolucaoDeLivro(user, livro));
		_system.logOffMaster();
	}
}
