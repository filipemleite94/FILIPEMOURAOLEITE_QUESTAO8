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
		return false;
	}
	
	public boolean logInUsuario(Usuario _usuario){
		if(!acessarSistema(_usuario)){
			return false;
		}
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
		return bd.pegarLivro(_usuario, nome);		
	}
}
