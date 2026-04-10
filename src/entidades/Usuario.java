package entidades;


public class Usuario 
{
	private String nombre;
	private Long tiempoRespuesta;
	private int intento;
	
	public Usuario()
	{
		this.nombre = "";
		this.tiempoRespuesta = 0L;
		this.intento = 6;
	}
	
	public Usuario(String nombre)
	{
		this.nombre = nombre;
		this.tiempoRespuesta = 0L;
		this.intento = 6;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public Long getTiempoRespuesta() {
		return tiempoRespuesta;
	}

	public void setTiempoRespuesta(Long tiempoRespuesta) {
		this.tiempoRespuesta = tiempoRespuesta;
	}


	public int getIntento() {
		return intento;
	}

	public void setIntento(int intento) {
		this.intento = intento;
	}

	public void sumaPuntoLetraCoincideEnLugar()
	{
		 this.tiempoRespuesta += 3;
	}
	
	public void sumaPuntoLetraExiste()
	{
		this.tiempoRespuesta += 1;
	}
	
	public void aciertaPalabraCompleta()
	{
		this.tiempoRespuesta += (3*5);
	}
	
	
	
	public void descontarIntento()
	{
		this.intento -= 1;
	}
	
	
	
}
