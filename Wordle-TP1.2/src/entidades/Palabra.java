package entidades;

public class Palabra 
{
	private String palabra;
	
	
	public Palabra()
	{
		this.palabra = "";
	}
	
	public Palabra(String palabra)
	{
		this.palabra = palabra;
	}
	
	public boolean compararPalabra(String palabra)
	{
		return this.palabra.equals(palabra);
	}
	
	private boolean existeLetraPalabraSecreta(char l) 
	{
		boolean existe = false;
		
		for(int caracter = 0; caracter < this.palabra.length(); caracter ++)
		{
			existe = existe || (this.palabra.charAt(caracter) == l);
		}
		return existe;	
	}
	
	public String devolverPalabra()
	{
		return this.palabra;
	}

}
