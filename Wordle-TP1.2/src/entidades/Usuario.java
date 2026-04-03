package entidades;

public class Usuario 
{
	private String nombre;
	private int punto;
	private int intento;
	
	public Usuario()
	{
		this.nombre = "";
		this.punto = 0;
		this.intento = 0;
	}
	
	public Usuario(String nombre)
	{
		this.nombre = nombre;
		this.punto = 0;
		this.intento = 6;
	}


	public void sumaPuntoLetraCoincideEnLugar()
	{
		 this.punto += 3;
	}
	
	public void sumaPuntoLetraExiste()
	{
		this.punto += 1;
	}
	
	public void aciertaPalabraCompleta()
	{
		this.punto += (3*5);
	}
	
	public int restaPuntoLetraNoCoincide()
	{
		return this.punto -= 1;
	}
	

	public String retornarNombre() 
	{
		return this.nombre;
	}
	
	public int retornarPuntos()
	{
		return this.punto;
	}
	
	public void descontarIntento()
	{
		this.intento -= 1;
	}
	
	public int mostrarIntento()
	{
		return this.intento;
	}
	
	
}
