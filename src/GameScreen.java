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
    private int cactusSpawnTimer = 0; // Temporizador para el spawn de cactus
    private final int CACTUS_SPAWN_INTERVAL = 200; // Intervalo en frames para generar cactus
    private final Land land;
    private boolean isRunning = true;
    private final List<Cactus> cactusList;
    private Character character;

    private Thread thread;
    
    private Cactus cactus;
    // private List<Image> cactusList;
    public Image dinoImg;
    public Image cactus1Img;
    
    // Logica
    public GameScreen(){
        // Solución a la pantalla en blanco
        setPreferredSize(new Dimension(width, height));
        character = new Character(GROUND, GRAVITY);
        land = new Land(App.SCREEN_WIDTH, character);
        cactusList = new ArrayList<Cactus>();
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
                if(isRunning){

                    character.update();
                    land.update();
                    
                    // Incrementa el temporizador para el cactus
                    cactusSpawnTimer++;
                    if (cactusSpawnTimer >= CACTUS_SPAWN_INTERVAL) {
                        // Crea un nuevo cactus en la posición (600, 240) y lo agrega a la lista
                        cactusList.add(new Cactus(600, 240)); // Nueva instancia de Cactus en cada ciclo
                        cactusSpawnTimer = 0; // Reinicia el temporizador
                    }
                    // Actualiza y verifica colisiones
                for (int i = 0; i < cactusList.size(); i++) {
                    Cactus cactus = cactusList.get(i);
                    cactus.update();
                    
                    if (isColision(cactus, character)) {
                        System.out.println("Colisión detectada");
                        character.setImage(new ImageIcon(getClass().getResource("./resource/dino-dead.png")).getImage());
                        isRunning = false;
                    }
    
                    // Elimina cactus si salen de la pantalla
                    if (cactus.isOutOfScreen()) {
                        cactusList.remove(i);
                        i--; // Ajusta el índice al eliminar un elemento
                    }
                }
        
                    // Actualiza la pantalla
                    revalidate();
                    repaint();
                    try {
                        Thread.sleep(16); // Pausa para suavizar animación
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            
        }
    }
    

    // dibujo
    @Override
    public void paint(Graphics graphics){
        // super para que grafique correctamente
        super.paint(graphics);
        // linea del suelo
        // graphics.drawRect(0, (int)GROUND, getWidth(), 0);
        // Dibujar el suelo
        land.draw(graphics);
        character.paint(graphics);
        for (Cactus cactus : cactusList) {
            cactus.draw(graphics);
        }
        if(!isRunning){
            graphics.setFont(new Font("Arial", Font.BOLD, 36));
            graphics.drawString("Game Over", width / 2 - 100, height / 2);
        }
    }

    // detectar colisiones
    public boolean isColision(Cactus cac, Character cha){
        Rectangle cacHitbox = cac.getHitbox();
        Rectangle chaHitbox = cha.getHitbox();
        return cacHitbox.intersects(chaHitbox);
    }

    // Metodos de acciones del teclado
    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println("Presionaste una tecla");
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            // Detectar si el juego está en pausa y reinciarlo
            if(!isRunning){
                System.out.println("Reiniciando juego...");
                cactusList.clear();
                character.reset();
                isRunning = true;
            }else{
                System.out.println("Jump");
                character.jump(); 
            }   
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}