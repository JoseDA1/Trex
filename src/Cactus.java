import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
public class Cactus {
    // private Image image;
    private Image img;
    private Rectangle rectangle;
    private int positionX, positionY;
    public Cactus(Image img){
        // this.img = new ImageIcon(getClass().getResource("./resource/cactus1.png")).getImage();
        // System.out.println(this.img);
        this.img = img;
        System.out.println(img);
        // System.out.println(this.img);
        positionX = 400;
        positionY = 240;
        try {
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Imagen del cactus no cargada");
        }
        rectangle = new Rectangle();
    }
    public void update(){
        positionX -=2;
        rectangle.x = positionX;
        rectangle.y = positionY;
        // rectangle.width = img.getWidth(null);
        // int right = rectangle.x + rectangle.width;
        // rectangle.height = img.getHeight(null);
    }
    public Rectangle getColision(){
        return rectangle;
    }

    public void paint(Graphics graphics, Image image){
        System.out.println(image);
        graphics.drawImage(image, positionX, positionY, null);
    }
}
