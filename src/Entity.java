import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
    public int positionX;
    public int positionY;
    public Rectangle hitbox;
    Entity(int positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;
        this.hitbox = new Rectangle();
    }
    public abstract void update();
    public abstract void draw(Graphics graphics);
    public Rectangle getHitbox(){
        return hitbox;
    }
}
