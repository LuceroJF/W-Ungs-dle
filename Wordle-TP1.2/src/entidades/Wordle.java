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
	
	
	//descontar intento
	public void descontarIntento() {
		usuario.descontarIntento();
	}
	
	
	
	//Metodo para verificar la palabra
	public String[] evaluarIntento(String intento) 
	{
	    String palabraSecreta = palabra.devolverPalabra();
	    String[] resultado = new String[5];

	    for (int i = 0; i < 5; i++) 
	    {
	        char letraIntento = intento.charAt(i);
	        char letraSecreta = palabraSecreta.charAt(i);

	        if (letraIntento == letraSecreta) 
	        {
	            resultado[i] = "VERDE";
	        } 
	        else if (palabraSecreta.contains(String.valueOf(letraIntento))) 
	        {
	            resultado[i] = "AMARILLO";
	        } 
	        else 
	        {
	            resultado[i] = "GRIS";
	        }
	    }

	    return resultado;
	}
	
	///////////agregado ////////////////
	public String nombreUsuario()
	{
		return this.usuario.retornarNombre();
	}
	
	//Para obtener la palabra secreta y mostarla en la pantalla final
	public String getPalabraSecreta() {
	    return palabra.devolverPalabra();
	}
	
	
	
	
}
