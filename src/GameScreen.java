// import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameScreen extends JPanel implements Runnable, KeyListener{
    private final double GRAVITY = 0.3;
    private final double GROUND = 300;
    int width = 400;
    int height = 400;

    public Character character;

    private Thread thread;
    
    private Cactus cactus;
    
    public Image dinoImg;
    public Image cactus1Img;
    
    // Logica
    public GameScreen(){
        loadImg();
        character = new Character(GROUND, GRAVITY);
        System.out.println(cactus1Img);
        cactus = new Cactus(cactus1Img);
        // permite recibir el focus del teclado
        setFocusable(true);
        // escucha los eventos del teclado
        addKeyListener(this);
        // setBackground(Color.RED);
        thread = new Thread(this);
        
    }

    // iniciliza los hilos
    public void startGame(){
        
        thread.start();
    }

    @Override
    // Se ejecuta cuando se llama al metodo start
    public void run() {
        while (true) {
            // System.out.println(i++);
            try {
                character.update();
                cactus.update();
                if(cactus.getColision().intersects(character.getColision())){
                    System.out.println("COLISION");
                }
                repaint();
                Thread.sleep(20); // Pausa para suavizar animación
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    

    public void loadImg(){
        try {
            // Toma la imagen en dinoImg
            dinoImg = new ImageIcon(getClass().getResource("./resource/dino-run.gif")).getImage();
            cactus1Img = new ImageIcon(getClass().getResource("./resource/cactus1.png")).getImage();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Imagen no encontrada");
        }
    }
    // dibujo
    public void paint(Graphics graphics){
        // super para que grafique correctamente
        super.paint(graphics);
        // suelo
        // linea del suelo
        graphics.drawRect(0, (int)GROUND, getWidth(), 0);
        // Dibujar el suelo
        // System.out.println(cactus1Img);
        cactus.paint(graphics, cactus1Img);
        character.paint(graphics, dinoImg);
    }

    // saltos
    

    // Metodos de acciones del teclado
    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println("Presionaste una tecla");
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            character.jump();   
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}