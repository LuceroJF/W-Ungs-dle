package entidades;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
public class AudioControladora {
    private Clip clip;

    public void cargarSonido(String ruta) {
        try {
            URL url = getClass().getResource(ruta);
            if (url == null) {
                System.err.println("No se encontró el recurso en la ruta: " + ruta);
                return;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void reproducir() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }
    public void repetirInfinito() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    public void detener() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}