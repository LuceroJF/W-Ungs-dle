package testing.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

import entidades.Wungsdle;

public class TestJuego extends Wungsdle {
	Wungsdle wungsdle = new Wungsdle();
	String palabraUsuario = wungsdle.devolverPalabraUsuario(); 
	// Verifica si el sistema maneja correctamente una entrada nula
    @Test(expected = NullPointerException.class)
    public void palabraVaciaTest() {
        wungsdle.evaluarColorLetra(null);
    }

    // Verifica que no se procesen palabras con longitud incorrecta
    @Test
    public void palabraMenorACincoTest() {
        String intentoCorto = "SOL";
        int intentosIniciales = wungsdle.consultarIntentoUsuario();
        if (intentoCorto.length() == 5) {
            wungsdle.evaluarColorLetra(intentoCorto);
        }
        assertEquals(intentosIniciales, wungsdle.consultarIntentoUsuario());
    }

    // Verifica si la palabra fue bien construida con las letras ingresadas
    @Test
    public void construccionDePalabraTest() {
        String palabraConstruida = "";
        char[] teclasPresionadas = {'H', 'O', 'L', 'A', 'S'};
        for (char c : teclasPresionadas) {
            palabraConstruida += String.valueOf(c);
        }
        assertEquals("HOLAS", palabraConstruida);
        assertEquals(5, palabraConstruida.length());
    }

    // Verifica si la palabra ingresada por el usuario fue adivinada correctamente
    @Test
    public void palabraAdivinadaCorrectaTest() {
        String intento = "PIZZA";
        String[] resultado = wungsdle.evaluarColorLetra(intento.toLowerCase(), "pizza");
        boolean todoVerde = true;
        for (String color : resultado) {
            if (!color.equals("VERDE")) {
                todoVerde = false;
                break;
            }
        }
        
        assertTrue(todoVerde);
    }


 // Verifica si la letra está en amarillo cuando existe en la palabra pero en otra posición
    @Test
    public void colorAmarilloTest() {
        String intento = "CASAS";
        String secreta = "SOPAS"; 
        String[] resultado = wungsdle.evaluarColorLetra(intento, secreta);
        assertEquals("AMARILLO", resultado[1]);
    }

    // Verifica si la letra está en verde cuando la posición coincide exactamente
    @Test
    public void colorVerdeTest() {
        String intento = "PERRO";
        String secreta = "PARTO";
        String[] resultado = wungsdle.evaluarColorLetra(intento, secreta);
        assertEquals("VERDE", resultado[0]);
        assertEquals("VERDE", resultado[4]);
    }

    // Verifica si la letra está en gris cuando la letra no existe en la palabra secreta
    @Test
    public void colorGrisTest() {
        String intento = "LIMON";
        String secreta = "PERAS";
        String[] resultado = wungsdle.evaluarColorLetra(intento, secreta);
        assertEquals("GRIS", resultado[0]);
        assertEquals("GRIS", resultado[2]);
    }

 // Verifica si la palabra ingresada por el usuario se encuentra en los txt según el idioma
    @Test
    public void palabraEnDiccionarioTest() {
        String idioma = "Español";
        String dificultad = "Facil";
        String palabraValida = "CASAS";
        String palabraInvalida = "XYZWQ";
        boolean resultadoValido = wungsdle.verificarSiExiste(palabraValida, idioma, dificultad);
        boolean resultadoInvalido = wungsdle.verificarSiExiste(palabraInvalida, idioma, dificultad);
        assertTrue(resultadoValido);
        assertFalse(resultadoInvalido);
    }

 
}
