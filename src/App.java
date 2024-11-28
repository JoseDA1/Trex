// import java.awt.Graphics;
import javax.swing.*;
public class App {

    private static GameScreen gameScreen;

    public static void window(int width, int height){
    // inicializar el fram
    JFrame frame = new JFrame("TRex Game");
    frame.setVisible(true);
    frame.setSize(width, height);
    // Coloca la ventana en el centro de la pantalla
    frame.setLocationRelativeTo(null);
    // Cambia el tamaño de la ventana si se ajusta manualmente
    frame.setResizable(true);
    // finaliza cuando se cierra la ventana
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    gameScreen = new GameScreen();
    // añade al frame lo que genera la clase
    frame.add(gameScreen);
    //
    gameScreen.requestFocus();
    }
    // llama al metodo que iniciará los hilos
    public static void startGame(){
        gameScreen.startGame();
        // gameScreen.loadImg();
    }
    public static void main(String[] args) throws Exception {
        int width = 400;
        int height = 400;
        // llama a el metodo window y le pasa los parametros
        window(width, height);
        // llama a startGame
        startGame();
        
    }
}
