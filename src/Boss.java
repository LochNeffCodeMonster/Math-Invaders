import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Boss {
	
	private static int alien_x = 0;
	private static int alien_y = -100;
	private int location;
	private int shotLocation;
	private int shotY;
	private int location_y;
	private static int numberOfBosses;
	private Image boss;
	private int number;
	
	private boolean alive;
	private boolean isShooting;
	private boolean afterlife;
	private boolean shoot = false;
	
	private static int speed = 1;
	private static int downSpeed = 1;
	
	public Boss(String s) {
		numberOfBosses++;
		Random rand = new Random();
		int r = rand.nextInt(50 * numberOfBosses) + 20 * numberOfBosses;
		number = r;
		ImageIcon a = new ImageIcon(s);
		boss = a.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		alive = true;
		afterlife = false;
	}
	
	public Image getBoss() {
		return boss;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void explodeAlien() {
		alive = false;
	}
	
	public void afterLife() {
		afterlife = true;
	}
	
	
	public boolean inAfterLife() {
		return afterlife;
	}
	
	public void shoot() {
		int slx = location;
		shotLocation = slx;
		int sly = location_y + 30;
		shotY = sly;
		shoot = true;
	}
	
	public void holdFire() {
		shoot = false;
	}
	
	public int getShotLocation() {
		return shotLocation;
	}
	
	public void shotDown() {
		shotY += 5;
	}
	
	public int getShot() {
		return shotY;
	}
	
	public int getLocation() {
		return location;
	}
	
	public int getLocationY() {
		return location_y;
	}
	
	public void moveDown() {
		alien_y += 1;
	}
	
	public int getY() {
		return alien_y;
	}
	
	public boolean isShooting() {
		return shoot;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void setAlienX(int num) {
		alien_x = num;
	}
	
	public void setAlienY(int num) {
		alien_y = num;
	}

}
