

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Alien extends ImageIcon {
	
	private static int alien_x = 0;
	private static int alien_y = 0;
	private int location;
	private int shotLocation;
	private int shotY;
	private int location_y;
	private static int numberOfAliens;
	private Image alien;
	private int number;
	
	private boolean alive;
	private boolean afterlife;
	private boolean shoot = false;
	
	private static int speed = 1;
	private static int downSpeed = 1;
	
	public Alien(String s) {
		numberOfAliens++;
		Random rand = new Random();
		int r = rand.nextInt(10);
		number = r;
		if (numberOfAliens == 6) {
			location = alien_x;
			location_y = alien_y;
			alien_x += 270;
		}
		if (numberOfAliens < 21) {
			location = alien_x;
			location_y = alien_y;
			alien_x += 70;
		}
		if (numberOfAliens == 11) {
			alien_x = 0;
			alien_y = alien_y + 90;
			location = alien_x;
			location_y = alien_y;
			alien_x += 70;
		}
		if (numberOfAliens == 15) {
			alien_x += 270;
		}
		
		
		ImageIcon a = new ImageIcon(s);
		alien = a.getImage().getScaledInstance(85, 85, Image.SCALE_DEFAULT);
		alive = true;
		afterlife = false;
	}
	public Alien(String s, int x, int y) {
		numberOfAliens++;
		location = x;
		location_y = y;
		Random rand = new Random();
		int r = rand.nextInt(10);
		number = r;
		
		ImageIcon a = new ImageIcon(s);
		alien = a.getImage().getScaledInstance(85, 85, Image.SCALE_DEFAULT);
		alive = true;
		afterlife = false;
	}
	
	
	public Image getAlien() {
		return alien;
	}
	
	public void explodeAlien() {
		numberOfAliens--;
		alive = false;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void afterLife() {
		afterlife = true;
	}
	
	public void resetSpeeds() {
		speed = 1;
		downSpeed = 1;
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
		shotY += 10;
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
	
	public void moveRight() {
		location += speed;
	}
	
	public void moveLeft() {
		location -= speed;
	}
	
	public void moveDown() {
		location_y += 10;
	}
	public void moveDown2() {
		location_y += 60;
	}
	public void moveDown3() {
		location_y += 5;
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
