import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
// import java.util.Random;
import javax.swing.ImageIcon;
public class Bird extends Entity{
    // public Entity entity;

    // private Random random;
    public Image imgBird, image;
    private final Rectangle rectangle;
    public int positionX, positionY;
    public int height, width;
    public Bird(int positionX, int positionY){
        super(positionX, positionY);
        // random = new Random();
        
        // random();
        this.positionX = positionX;
        this.positionY = positionY;
    
        rectangle = new Rectangle();
        try {
            image = new ImageIcon(getClass().getResource("./resource/bird.gif")).getImage();
            // System.out.println("bird file encontrado");
            // System.out.println("Imagenes de cactus encontradas");
        } catch (Exception e) {
            System.out.println("Imagenes de pajaros no encontradas " + e.getMessage());
        }
        // image = getRandomCactus();
        // System.out.println(image);
        height = image.getHeight(null) - 20;
        width = image.getWidth(null) - 20;
        hitbox = new Rectangle(this.positionX, this.positionY, width, height);
        // System.out.println(hitbox);
    }

    @Override
    public void update(){
        positionX -=5;
        rectangle.x = positionX;
        rectangle.y = positionY;
        rectangle.width = width;
        rectangle.height = height;
    }
    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(image, positionX, positionY, null);

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

    // public Image getRandomCactus(){
    //     positionY = 240;
    //     Image img;
        
    //     int randomValue = random.nextInt(3); // Genera un número aleatorio: 0, 1 o 2
    //     img = switch (randomValue) {
    //         case 0 -> imgCactus1;
    //         case 1 -> imgCactus2;
    //         default -> imgCactus3;
    //     };
    //     return img;
    // }

    public boolean isOutOfScreen() {
        return positionX + image.getWidth(null) < 0; // Devuelve true si el cactus salió de la pantalla
    }
    public int getX(){
        return this.positionX;
    }
    
    public int getY(){
        return this.positionY;
    }

}
