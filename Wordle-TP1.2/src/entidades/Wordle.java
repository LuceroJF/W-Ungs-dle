package entidades;

public class Wordle 
{
	private Usuario usuario;
	private Palabra palabra;
	
	public Wordle()
	{
		this.usuario = null;
		this.palabra = null;
	}

	public Wordle(Usuario usuario, Palabra palabra)
	{
		this.usuario = usuario;
		this.palabra = palabra;
	}
	
	public boolean comparaPalabraUsuario(String palabraUsuario)
	{
		return this.palabra.compararPalabra(palabraUsuario);
	}
	
	public int consultarPuntosUsuario()
	{
		return this.usuario.retornarPuntos();
	}
	
	public int consultarIntentoUsuario()
	{
		return this.usuario.mostrarIntento();
	}
	
	
}
