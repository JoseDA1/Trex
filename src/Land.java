import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Land {
	
	public static final int LAND_POSY = 285;
	
	private final List<ImageLand> LIST_LAND;
	private final BufferedImage LAND1;
	private final BufferedImage LAND2;
	private final BufferedImage LAND3;
	
	private final Character Character;
	
	public Land(int width, Character Character) {
		this.Character = Character;
		
		LAND1 = Resource.getResourceImage("bin/resource/land1.png");
		LAND2 = Resource.getResourceImage("bin/resource/land2.png");
		LAND3 = Resource.getResourceImage("bin/resource/land3.png");
		// System.out.println("LAND 1 ES: " + LAND1);
		int numberOfImageLand = width / LAND1.getWidth() + 2;
		LIST_LAND = new ArrayList<>();
		for(int i = 0; i < numberOfImageLand; i++) {
			ImageLand imageLand = new ImageLand();
			imageLand.posX = i * LAND1.getWidth();
			setImageLand(imageLand);
			LIST_LAND.add(imageLand);
		}
	}
	
    //actualizacion del suelo
	public void update(){
		Iterator<ImageLand> itr = LIST_LAND.iterator();
		ImageLand firstElement = itr.next();
		firstElement.posX -= Character.getX();
		float previousPosX = firstElement.posX;
		while(itr.hasNext()) {
			ImageLand element = itr.next();
			element.posX = previousPosX + LAND1.getWidth();
			previousPosX = element.posX;
		}
		if(firstElement.posX < -LAND1.getWidth()) {
			LIST_LAND.remove(firstElement);
			firstElement.posX = previousPosX + LAND1.getWidth();
			setImageLand(firstElement);
			LIST_LAND.add(firstElement);
		}
	}
	
    
	private void setImageLand(ImageLand imageLand) {
		int typeLand = getTypeOfLand();
            switch (typeLand) {
                case 1:
                    imageLand.image = LAND1;
                    break;
                case 3:
                    imageLand.image = LAND3;
                    break;
                default:
                    imageLand.image = LAND2;
                    break;
            }
	}
	

	public void draw(Graphics g) {
		for(ImageLand imgLand : LIST_LAND) {
			g.drawImage(imgLand.image, (int) imgLand.posX, LAND_POSY, null);
		}
	}
	
    //randomizador para el tipo de suelo
	private int getTypeOfLand() {
		Random rand = new Random();
		int type = rand.nextInt(10);
            return switch (type) {
                case 1 -> 1;
                case 9 -> 3;
                default -> 2;
            };
	}
	
	private class ImageLand {
		float posX;
		BufferedImage image;
	}
	
}
