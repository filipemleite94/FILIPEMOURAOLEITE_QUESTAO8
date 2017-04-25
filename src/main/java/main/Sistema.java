package main;

import static org.mockito.Mockito.*;

public class Sistema {
	BD bd;
	boolean souBibliotecaria;
	Usuario _usuario;
	
	public Sistema(){
		bd = mock(BD.class);
		when(bd.inserirUsuario(isA(Usuario.class))).thenReturn(true);
		when(bd.removerUsuario(isA(Usuario.class))).thenReturn(true);
		when(bd.tempoDeBloqueio(isA(Usuario.class))).thenReturn(Integer.MAX_VALUE);
		souBibliotecaria = false;
		_usuario = null;
	}
	
	public boolean logInMaster(String senha){
		souBibliotecaria = true;
		return true;
	}
	
	public boolean logOffMaster(){
		souBibliotecaria = false;
		return true;
	}
	
	public boolean logInUsuario(Usuario _usuario){
		if(!acessarSistema(_usuario)){
			return false;
		}
		souBibliotecaria = false;
		this._usuario = _usuario;
		return true;
	}
	
	public boolean logOffUsuario(){
		_usuario = null;
		return true;
	}
	
	public boolean acessarSistema(Usuario _usuario){
		return bd.podeAcessarOSistema(_usuario);
	}
	
	public boolean inserirUsuario(Usuario _usuario){
		if(!souBibliotecaria){
			return false;
		}
		if(!bd.inserirUsuario(_usuario)){
			return false;
		};
		doReturn(0).when(bd).tempoDeBloqueio(_usuario);
		return true;
	}
	
	public boolean removerUsuario(Usuario _usuario){
		if(!souBibliotecaria){
			return false;
		}
		if(!bd.removerUsuario(_usuario)){
			return false;
		}
		doReturn(false).when(bd).podeAcessarOSistema(_usuario);
		return true;
	}
	
	public int bloquearUsuario(Usuario _usuario, int tempo){
		if(!souBibliotecaria){
			return bd.tempoDeBloqueio(_usuario);
		}
		doReturn(tempo).when(bd).tempoDeBloqueio(_usuario);
		return bd.tempoDeBloqueio(_usuario);
	}
	
	public Livro pegarLivro(String nome){
		if(_usuario==null||souBibliotecaria){
			return null;
		}
		if(bd.tempoDeBloqueio(_usuario)>0){
			return null;
		}
		return bd.pegarLivro(_usuario, nome);		
	}
	
	public boolean registrarEmprestimoDeLivro(Usuario user, Livro livro, int tempo){
		if(!souBibliotecaria){
			return false;
		}
		doReturn(null).when(bd).pegarLivro(isA(Usuario.class), eq(livro.getNome()));
		bloquearUsuario(_usuario, tempo);
		return true;
	}
	
	public boolean registrarDevolucaoDeLivro(Usuario user, Livro livro){
		if(!souBibliotecaria){
			return false;
		}
		doReturn(livro).when(bd).pegarLivro(isA(Usuario.class), eq(livro.getNome()));
		bloquearUsuario(_usuario, 0);
		return true;
	}
}
