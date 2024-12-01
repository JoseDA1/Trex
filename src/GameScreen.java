// import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameScreen extends JPanel implements Runnable, KeyListener{
    private final double GRAVITY = 0.4;
    private final double GROUND = 300;
    int width = 600;
    int height = 400;
    private int spawnTimer = 0; // Temporizador para el spawn de entidades
    private int spawnInterval = 200; // Intervalo en frames para generar entidades
    private Random random;
    private final Land land;
    private boolean isRunning = true;
    private List<Bird> birdList;
    private List<Cactus> cactusList;
    private Character character;
    
    private Thread thread;

    private Bird bird;
    private Cactus cactus;
    // private List<Image> cactusList;
    public Image dinoImg;
    public Image cactus1Img;
    
    // Logica
    public GameScreen(){
        // Solución a la pantalla en blanco
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE); // Fondo blanco
        
        character = new Character(GROUND, GRAVITY);
        land = new Land(App.SCREEN_WIDTH, character);
        cactusList = new ArrayList<Cactus>();
        birdList = new ArrayList<Bird>();
        random = new Random();
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
                    character.upScore();
                    // Incrementa el temporizador para el cactus
                    spawnTimer++;
                    if (spawnTimer >= spawnInterval) {
                        int chance = random.nextInt(100); // Genera un número entre 0 y 99
                        if (chance < 70) { // 70% para cactus
                            cactusList.add(new Cactus(600, 240));
                        } else { // 30% para pájaro
                            // birdList.add(new Bird(600, 100));
                            birdList.add(new Bird(600, 200));
                        }
                        // reduccion de intervalo para hacer el juego más dificil
                        if(spawnInterval > 50){
                            spawnInterval -= 10;
                            System.out.println(spawnInterval);
                        }
                        // for (Bird bird : birdList) {
                        //     System.out.println("Bird position: " + bird.getX() + ", " + bird.getY());
                        // }
                        // System.out.println(birdList);
                        // System.out.println(cactusList);
                        // Crea un nuevo cactus en la posición (600, 240) y lo agrega a la lista
                        spawnTimer = 0; // Reinicia el temporizador
                    }
                    // Actualiza y verifica colisiones de los cactus
                    for (int i = 0; i < cactusList.size(); i++) {
                        cactus = cactusList.get(i);
                        cactus.update();
                        
                        if (isColision(cactus, character)) {
                            // System.out.println("Colisión detectada");
                            character.setImage(new ImageIcon(getClass().getResource("./resource/dino-dead.png")).getImage());
                            isRunning = false;
                        }
        
                        // Elimina cactus si salen de la pantalla
                        if (cactus.isOutOfScreen()) {
                            cactusList.remove(i);
                            i--; // Ajusta el índice al eliminar un elemento
                        }
                    }
                    // colisiones de los pajaros
                    for (int i = 0; i < birdList.size(); i++) {
                        bird = birdList.get(i);
                        bird.update();
                        
                        if (isColision(bird, character)) {
                            // System.out.println("Colisión detectada");
                            character.setImage(new ImageIcon(getClass().getResource("./resource/dino-dead.png")).getImage());
                            isRunning = false;
                        }
        
                        // Elimina cactus si salen de la pantalla
                        if (bird.isOutOfScreen()) {
                            birdList.remove(i);
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
        // Dibujar el suelo
        land.draw(graphics);
        // pinta al dino
        character.paint(graphics);
        // Score
        graphics.drawString("HI " + Character.score, 500, 20);
        // array de cactus
        for (Cactus cactus : cactusList) {
            cactus.draw(graphics);
        }
        // recorrer el array de pajaros
        for (Bird bird : birdList) {
            bird.draw(graphics);
        }
        if(!isRunning){
            graphics.setFont(new Font("Arial", Font.BOLD, 36));
            graphics.drawString("Game Over", width / 2 - 100, height / 2);
        }
    }

    // detectar colisiones del cactus
    public boolean isColision(Cactus cac , Character cha){
        Rectangle cacHitbox = cac.getHitbox();
        Rectangle chaHitbox = cha.getHitbox();
        return cacHitbox.intersects(chaHitbox);
    }
    // colisiones pajaro
    public boolean isColision(Bird bird, Character cha){
        Rectangle biHitbox = bird.getHitbox();
        Rectangle chaHitbox = cha.getHitbox();
        return biHitbox.intersects(chaHitbox);
    }

    // Metodos de acciones del teclado
    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println("Presionaste una tecla");
        if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP){
            // Detectar si el juego está en pausa y reinciarlo
            if(!isRunning){
                // System.out.println("Reiniciando juego...");
                cactusList.clear();
                birdList.clear();
                character.reset();
                Character.score = 0;
                isRunning = true;
            }else{
                // System.out.println("Jump");
                character.jump(); 
            }   
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            // System.out.println("Crouch");
            character.crouch();
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            // System.out.println("Stand Up");
            character.standUp();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    
}