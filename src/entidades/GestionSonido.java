package entidades;

public class GestionSonido {
    public static AudioControladora sonidoTipeo = new AudioControladora();
    public static AudioControladora sonidoClick = new AudioControladora();
    public static AudioControladora musicaFondo = new AudioControladora();
    public static AudioControladora gameOverSound = new AudioControladora();
    public static AudioControladora winningSound = new AudioControladora();
    public static AudioControladora palabraInvalida = new AudioControladora();

    public static void inicializar() {
        sonidoTipeo.cargarSonido("/recursos/typingSound.wav");
        sonidoClick.cargarSonido("/recursos/menuClick.wav");
        musicaFondo.cargarSonido("/recursos/gameSound.wav");
        gameOverSound.cargarSonido("/recursos/gameOverSound.wav");
        winningSound.cargarSonido("/recursos/winningSound.wav");
        palabraInvalida.cargarSonido("/recursos/palabraInvalida.wav");
    }
}