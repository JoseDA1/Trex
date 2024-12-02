import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class reproductorSonidos {
    private static Clip clip;

    public static void reproducirSonido(String rutaArchivo) {
        new Thread(() -> {
            try {
                // Cargar el archivo de audio
                File archivoAudio = new File(rutaArchivo);
                if (!archivoAudio.exists()) {
                    System.out.println("Archivo de audio no encontrado: " + rutaArchivo);
                    return;
                }

                // Detener cualquier audio que esté sonando
                detenerSonido();

                AudioInputStream flujoAudio = AudioSystem.getAudioInputStream(archivoAudio);

                // Configurar el reproductor
                clip = AudioSystem.getClip();
                clip.open(flujoAudio);

                // Configurar para que reproduzca los últimos 2 segundos
                long duracionTotal = clip.getMicrosecondLength(); // Duración total en microsegundos
                long inicioReproduccion = Math.max(0, duracionTotal - 1_000_000); // Últimos 2 segundos
                clip.setMicrosecondPosition(inicioReproduccion);

                // Iniciar reproducción
                clip.start();
                clip.loop(0); // Reproducir una vez

                // Cerrar el clip al finalizar
                clip.addLineListener(evento -> {
                    if (evento.getType() == javax.sound.sampled.LineEvent.Type.STOP) {
                        clip.close();
                    }
                });

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Método para detener el sonido
    public static void detenerSonido() {
        if (clip != null && clip.isRunning()) {
            clip.stop(); // Detener la reproducción
        }
    }
}
