La última actualización contiene cambios en todas las siguientes instancias;
-Finalmente cuando vamos a Configuración -> English -> Aplicar cambios, TODOS los botones e imágenes cambian al idioma seleccionado (ingles o español) 
-Se agregó una nueva clase Test que contiene, no muchos, pero suficientes test que permiten verificar que nuestro código está funcionando "bien" 
- - - ( Necesario aclarar que de todos los test, hay uno que todavía me gustaría debatir con ustedes porque realmente ya eran las 5 de la mañana y estaba tan cansado de codear que lo recontra copie del gemini)
-Se agregó la funcionalidad que permite que si el usuario escribe cualquier cosa, como por ejemplo "wwwww", no se le permita adivinar, y que realmente deba ingresar una palabra válida 
- - - (Las palabras "válidas" se toman del gigantesco "diccionario" que en realidad son los 4 txt con sus respectivos idiomas, unas 2 mil palabras mas o menos) 

Explicación breve de cómo todo esto pasó a funcionar;
- - Dentro de WUNGSDLE se encuentran métodos del estilo 
- - - 	
	public String getTextoIngresarNombre() {
	    if (this.idiomaActual.startsWith("English")) {
	        return "Enter your name";
	    }
	    return "Ingrese su nombre";
	}
- - - 
- - Estos métodos simplemente le preguntan a la instancia de la clase de negocio ¿Cuál es el idioma actual del juego? y deciden acorde cuál será el STRING que le setea al botón que corresponda.

- - Luego, y quizá más importante, es el código que se creó para la interfaz de inicio (el menú);
- - - 
	public void actualizarTextos() {
	    iniciarJuego=(wordle.getTextoBotonInicio());
	    configuracion=(wordle.getTextoConfiguracion());
	    
	}
	/// Este está duplicado para diferenciarlo del que empieza con el main (el de arriba) y el que es actualizado por la configuracion (este de abajo)
	public void actualizarTextos(String nuevo) {
	    iniciarJuego=(wordle.getTextoBotonInicio());
		InterfazInicio nuevaInterfazIdiomaActual = new InterfazInicio(wordle);
		nuevaInterfazIdiomaActual.setVisible(true);
		nuevaInterfazIdiomaActual.setLocationRelativeTo(null);
		this.dispose();
		
	}
- - -
- - Como se puede apreciar, "actualizarTextos()" es llamado desde el main, para que de esta manera una vez que el menu se crea, no aparezca "vacío".
- - Y como bien está explicado dentro del código con un comentario, "ActualizarTextos(String nuevo)" es simplemente un constructor diferente que lo extrapola de su contraparte "actualizarTextos()", 
- - para este ser llamado desde la interfaz de configuración de la siguiente manera; 
- - - 
	private void volverMenuPrincipal(ActionEvent accion) {
		menuPrincipal.actualizarTextos("nuevo");
		this.dispose();
	}
- - -
- - El motivo por el cual esto es necesario, es debido a que como las interfaces se inicializan en sí mismas, y una vez inicializadas no pueden "salir" de su bucle, para poder actualizar esta información
- - es necesario deshacerse del original y crear una nueva instancia con el idioma seleccionado, que llega desde la interfaz de configuración. 


	# Nuevos cambios

* **Nueva Clase de Configuración Inicial**: Nos ayuda a que la configuración de inicio pueda replicarse tanto cuando reiniciamos el juego como cuando comenzamos.
* **Valores por defecto**: Se agregó al constructor de `Wungsdle` el idioma y la dificultad por default.
* **Función de Alertas**: Se creó una función centralizada para el manejo de alertas.
* **Control de flujo (Enter)**: La tecla Enter no se activa hasta que el usuario hace clic en "Iniciar Juego" después de ingresar su nombre.
* **Gestión de memoria**: Ahora todas las pestañas se cierran correctamente al salir de ellas (uso de `dispose`).
* **Mejoras visuales**: Se actualizaron y mejoraron las tipografías de la interfaz.
* **Si la palabra no existe en el diccionario ahora se pone toda la linea en rojo
* **el ranking queda guardado por mas que cerremos el Juego.
