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
	
	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	
	public boolean compararPalabra(String palabra)
	{
		return palabra.equals(palabra);
	} 
	
}
