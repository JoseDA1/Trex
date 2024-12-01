// import java.awt.Gr
import java.awt.Color;
import javax.swing.*;

public class App {

    private static GameScreen gameScreen;
    

    public static final int SCREEN_WIDTH = 600;

    public static void window(int width, int height){
    // inicializar el fram
    JFrame frame = new JFrame("TRex Game");
    // Color blanco
    frame.getContentPane().setBackground(Color.WHITE);
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
        int width = 600;
        int height = 400;
        // llama a el metodo window y le pasa los parametros
        window(width, height);
        // llama a startGame
        startGame();
        
    }


}
