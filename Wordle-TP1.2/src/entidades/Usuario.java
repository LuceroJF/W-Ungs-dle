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


	public int sumaPuntoLetraCoincideEnLugar()
	{
		return this.punto += 3;
	}
	
	public int sumaPuntoLetraExiste()
	{
		return this.punto += 1;
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
