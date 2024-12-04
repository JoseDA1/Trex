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
        
        // random();
        this.positionX = positionX;
        this.positionY = positionY;
    
        rectangle = new Rectangle();
        try {
            image = new ImageIcon(getClass().getResource("./resource/bird.gif")).getImage();
        } catch (Exception e) {
            System.out.println("Imagenes de pajaros no encontradas " + e.getMessage());
        }
        height = image.getHeight(null) - 20;
        width = image.getWidth(null) - 20;
        hitbox = new Rectangle(this.positionX, this.positionY, width, height);
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
    }
    public Rectangle getHitbox(){
        return rectangle;
    }
    

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
