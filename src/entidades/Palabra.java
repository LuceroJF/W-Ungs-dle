package entidades;

public class Palabra 
{
	private String palabra;
	
	//===================CONSTRUCTORES===================//
	public Palabra()
	{
		this.palabra = "";
	}
	
	public Palabra(String palabra)
	{
		this.palabra = palabra;
	}
	//==================SETTER Y GETTERS=================//
	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	//===================METODOS=========================//
	public boolean compararPalabra(String palabra)
	{
		return palabra.equals(palabra);
	} 
	
}
