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
    public Cactus(){
        random = new Random();
        try {
            imgCactus1 = new ImageIcon(getClass().getResource("./resource/cactus1.png")).getImage();
            imgCactus2 = new ImageIcon(getClass().getResource("./resource/cactus2.png")).getImage();
            imgCactus3 = new ImageIcon(getClass().getResource("./resource/cactus3.png")).getImage();
        } catch (Exception e) {
            System.out.println("Imagenes de cactus no encontradas");
        }
        // random();
        positionX = 600;
        positionY = 240;
    
        rectangle = new Rectangle();
    }
    public void update(){
        positionX -=5;
        rectangle.x = positionX;
        rectangle.y = positionY;
        rectangle.width = imgCactus1.getWidth(null);
        // int right = rectangle.x + rectangle.width;
        rectangle.height = imgCactus1.getHeight(null);
    }
    // public void setImage(Image image){
    //     this.image = image;
    // }
    public Rectangle getColision(){
        return rectangle;
    }
    // public void paint(Graphics graphics){
    //     // System.out.println(img);
    //     graphics.drawImage(image, positionX, positionY, null);
    // }

    public Image getRandomCactus(){
        positionY = 240;
        positionX = 600;
        Image img;
        int randomValue = random.nextInt(3); // Genera un número aleatorio: 0, 1 o 2
        img = switch (randomValue) {
            case 0 -> imgCactus1;
            case 1 -> imgCactus2;
            default -> imgCactus3;
        };
        // if(random.nextBoolean()){
        //     img = imgCactus1;
        // }else{
        //     img = imgCactus2;
        // }
        return img;
    }
}
