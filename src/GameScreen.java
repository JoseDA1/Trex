// import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameScreen extends JPanel implements Runnable, KeyListener{
    private final double GRAVITY = 0.4;
    private final double GROUND = 300;
    int width = 600;
    int height = 400;

    private final Land land;
    private Character Character;

    private Thread thread;
    
    private Cactus cactus;
    private List<Image> cactusList;
    public Image dinoImg;
    public Image cactus1Img;
    
    // Logica
    public GameScreen(){
        // Solución a la pantalla en blanco
        setPreferredSize(new Dimension(width, height));
        try {
            // Toma la imagen en dinoImg
            dinoImg = new ImageIcon(getClass().getResource("./resource/dino-run.gif")).getImage();
            // cactus1Img = new ImageIcon(getClass().getResource("./resource/cactus1.png")).getImage();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Imagen no encontrada");
        }
        // loadImg();
        Character = new Character(GROUND, GRAVITY);
        land = new Land(App.SCREEN_WIDTH, Character);
        // System.out.println(cactus1Img);
        cactus = new Cactus();
        cactusList = new ArrayList<>();
        
        cactusList.add(cactus.getRandomCactus());
        System.out.println(cactusList);
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
                Character.update();
                cactus.update();
                land.update();
                if(cactus.getColision().intersects(Character.getColision())){
                    System.out.println("COLISION");
                }
                revalidate();
                repaint();
                Thread.sleep(20); // Pausa para suavizar animación
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    

    // dibujo
    @Override
    public void paint(Graphics graphics){
        // super para que grafique correctamente
        super.paint(graphics);
        // suelo
        // linea del suelo
        graphics.drawRect(0, (int)GROUND, getWidth(), 0);
        // Dibujar el suelo
        // System.out.println(cactus1Img);
        // cactus.paint(graphics);
        land.draw(graphics);
        Character.paint(graphics, dinoImg);
        for (Image imgCactus : cactusList) {
            // System.out.println(imgCactus);
            graphics.drawImage(imgCactus, cactus.positionX, cactus.positionY, null);
            graphics.drawRect(cactus.positionX, cactus.positionY, imgCactus.getWidth(null), imgCactus.getHeight(null));
            // graphics.drawImage(cactus1Img, 500, 240, null);
        }
    }

    // saltos
    

    // Metodos de acciones del teclado
    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println("Presionaste una tecla");
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            Character.jump();   
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}