import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;
public class Cactus extends Entity{
    // public Entity entity;

    private Random random;
    public Image imgCactus1, imgCactus2, imgCactus3,image;
    private final Rectangle rectangle;
    public int positionX, positionY;
    public Cactus(int startX, int startY){
        super(startX, startY);
        random = new Random();
        
        // random();
        positionX = 600;
        positionY = 240;
    
        rectangle = new Rectangle();
        try {
            imgCactus1 = new ImageIcon(getClass().getResource("./resource/cactus1.png")).getImage();
            imgCactus2 = new ImageIcon(getClass().getResource("./resource/cactus2.png")).getImage();
            imgCactus3 = new ImageIcon(getClass().getResource("./resource/cactus3.png")).getImage();
            // System.out.println("Imagenes de cactus encontradas");
        } catch (Exception e) {
            System.out.println("Imagenes de cactus no encontradas");
        }
        image = getRandomCactus();
        // System.out.println(image);
        hitbox = new Rectangle(positionX, positionY, image.getWidth(null), image.getHeight(null));
        // System.out.println(hitbox);
    }

    @Override
    public void update(){
        positionX -=5;
        rectangle.x = positionX;
        rectangle.y = positionY;
        rectangle.width = image.getWidth(null);
        rectangle.height = image.getHeight(null);
    }
    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(image, positionX, positionY, null);
        // Dibujar el hitbox (opcional, para depuración)
        // System.out.println("Cactus dibujado");
        graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        // graphics.drawRect(positionX-100, 200, 10, 10);
    }
    public Rectangle getHitbox(){
        return rectangle;
    }
    // public void paint(Graphics graphics){
    //     // System.out.println(img);
    //     graphics.drawImage(image, positionX, positionY, null);
    // }

    public Image getRandomCactus(){
        positionY = 240;
        Image img;
        
        int randomValue = random.nextInt(3); // Genera un número aleatorio: 0, 1 o 2
        img = switch (randomValue) {
            case 0 -> imgCactus1;
            case 1 -> imgCactus2;
            default -> imgCactus3;
        };
        return img;
    }

    public boolean isOutOfScreen() {
        return positionX + image.getWidth(null) < 0; // Devuelve true si el cactus salió de la pantalla
    }
}
