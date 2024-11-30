import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Character{
        private double x=50;
        private double y=300;
        private double velocityY = 0;
        private int height = 94;
        private int width = 88;

        private double ground;
        private double gravity;
        private Rectangle rectangle;
    
    public Character(double ground, double gravity){
        this.ground = ground;
        this.gravity = gravity;
        rectangle = new Rectangle();
    }
    public void update(){
            y+=velocityY;
            if(y >= this.ground-50){
            velocityY=0;
            y = this.ground-50;
        }else{
            velocityY+=this.gravity;
        }
        rectangle.x = (int)x;
        rectangle.y = (int)y;
        rectangle.width = width;
        rectangle.height = height;
    }
    public Rectangle getColision(){
        return rectangle;
    }
    public void jump(){
        if(ground-50 == y){
            velocityY = -6;   
        }else{
            // System.out.println("No jump " + y);
        }
    }
    public void paint(Graphics graphics, Image dinoImg){
        // pinta el dino
        graphics.drawImage(dinoImg, (int)x, (int) y, 50, 50, null);
        // cuadrado
        // graphics.drawRect((int)x, (int)y, 50, 50);
        
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
