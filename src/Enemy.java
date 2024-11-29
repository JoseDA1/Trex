import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
public class Enemy {
    public List<Entity> enemyList;
    public Cactus cactus;
    public Image image;
    public double positionX;
    public double positionY;
    Enemy(){
        enemyList = new ArrayList<Entity>();
        cactus = new Cactus();
        enemyList.add(cactus);
    }
    public void setImage(Image image){
        this.image = image;
    }
    public void paint(Graphics graphics){
        // for(Cactus entity : enemyList){
        //     entity.paint(graphics);
        // }
    }

}
