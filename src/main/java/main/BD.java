package main;

public class BD {
	public boolean inserirUsuario(Usuario _usuario){
		return true;
	}
	
	public boolean removerUsuario(Usuario _usuario){
		return true;
	}
	
	public boolean podePegarLivro(Usuario _usuario){
		if(tempoDeBloqueio(_usuario)<=0){
			return true;
		}
		return false;
	}
	
	public int tempoDeBloqueio(Usuario _usuario){
		return Integer.MAX_VALUE;
	}
	
	public boolean podeAcessarOSistema(Usuario _usuario){
		return true;
	}
	
	public Livro pegarLivro(Usuario _usuario, String Nome){
		if(!podePegarLivro(_usuario)){
			return null;
		}
		return new Livro();
	}
}
