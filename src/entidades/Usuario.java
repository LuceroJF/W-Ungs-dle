package entidades;


public class Usuario 
{
	private String nombre;
	private Long tiempoRespuesta;
	private int puntos;
	private int intento;
	
	//========================CONSTRUCTORES=======================//
	public Usuario()
	{
		this.tiempoRespuesta = 0L;
		this.intento = 6;
		this.puntos = 0;
	}
	
	public Usuario(String nombre)
	{
		this.nombre = nombre;
		this.tiempoRespuesta = 0L;
		this.intento = 6;
		this.puntos=0;
	}

	//========================GETTERS===================//
	public String getNombre() {
		return nombre;
	}
	
	public Long getTiempoRespuesta() {
		return tiempoRespuesta;
	}

	public int getIntento() {
		return intento;
	}
	
	public int getPuntos() {
		return this.puntos;
	}
	//========================SETTERS===================//
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setTiempoRespuesta(Long tiempoRespuesta) {
		this.tiempoRespuesta = tiempoRespuesta;
	}
	
	public void setIntento(int intento) {
		this.intento = intento;
	}
	
	//=========================METODOS======================//
	public void crearNombreUsuario(String nombre) {
		this.nombre = nombre;
	}
	
	public void sumaPuntoSiLetraCoincideEnLugar()
	{
		 this.tiempoRespuesta += 3;
	}
	
	public void sumaPuntoSiLetraExiste()
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

	public void sumarPuntos(int i) {
		this.puntos += i;
	}

	public boolean isNombreVacio() {
		return nombre.isEmpty();
	}
	
	
}
