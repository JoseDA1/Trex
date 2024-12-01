import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Character{
        private double x=50;
        private double y = 240;
        private double velocityY = 0;
        private int height;
        private int width;
        private Image img, imgDinoRun;
        private double ground;
        private double gravity;
        private Rectangle rectangle;
    
    public Character(double ground, double gravity){
        
        this.ground = (int) ground - 60;
        this.gravity = gravity;
        rectangle = new Rectangle();
        try {
            // Toma la imagen en dinoImg
            imgDinoRun = new ImageIcon(getClass().getResource("./resource/dino-run.gif")).getImage();
            img = imgDinoRun;
            // cactus1Img = new ImageIcon(getClass().getResource("./resource/cactus1.png")).getImage();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Imagen no encontrada");
        }
        height = (img.getHeight(null) - 20);
        width = (img.getWidth(null) - 20);
        // y = this.ground + height;
        
    }
    public void update(){
            y+=velocityY;
            if(y >= this.ground){
            velocityY=0;
            y = this.ground;
        }else{
            velocityY+=this.gravity;
        }
        rectangle.x = (int)x;
        rectangle.y = (int)y;
        rectangle.width = width;
        rectangle.height = height;
    }
    public Rectangle getHitbox(){
        return rectangle;
    }
    public void jump(){
        if(ground == y){
            velocityY = -11;   
        }else{
            // System.out.println("No jump " + y);
        }
    }
    public void reset(){
        x = 50;
        y = 240;
        velocityY = 0;
        setImage(imgDinoRun);
    }
    public void paint(Graphics graphics){
        // pinta el dino
        graphics.drawImage(img, (int)x, (int) y, width, height, null);
        graphics.drawRect((int)x, (int)y, width, height);
        // cuadrado
        // graphics.drawRect((int)x, (int)y, 50, 50);
        
    }

    public void setImage(Image img){
        this.img = img;
    }

    public double getX(){
        return this.x;
    }
    public void setX(double x){
        this.x = x;
    }

    public double getY(){
        return this.y;
    }
    public void setY(double y){
        this.y = y;
    }

    public double getVelocityY(){
        return this.velocityY;
    }
    public void setVelocityY(double velocityY){
        this.velocityY = velocityY;
    }
    public float getSpeedX() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSpeedX'");
    }

}
