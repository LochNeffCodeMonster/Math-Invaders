

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * This class creates a game board in a JPanel and controls all of the major components of the game.
 * 
 * @author Daniel Hawkins
 */

public class Board extends JPanel {
	
	private final int boardWidth = 1000; // sets the board width in pixels
	private final int boardHeight = 700; // sets the board height in pixels
	
	private Rectangle playButton = new Rectangle(boardWidth/2-100, 150, 200, 100); 
	private Rectangle quitButton = new Rectangle(boardWidth/2-100, 350, 200, 100);
	
	private static final double SCALE = 0.75; // Scaling variable for scaling the screen size to other computers
	private static final double INV_SCALE = 1.0 / SCALE;
	
	private int ship_x = boardWidth / 2; // player ship starting x-coordinate
	private int ship_y = boardHeight-60; // player ship staring y-coordinate
	private int explosion_x; // sets the x-coordinate of the explosion animation when an alien or player is destroyed
	private int explosion_y; // sets the y-coordinate of the explosion animation when an alien or player is destroyed
	private int laser_y = boardHeight-60; // sets the starting y-coordinate of the player laser
	private int laser_x; // variable to keep track of where the laser should be spawned when the player fires
	private static int score = 0; // keeps score for the game
	private static int lives = 5; // keeps track of the number of lives the player has
	private boolean inMenu = true;  
	private boolean inGame = false;
	private boolean leftDirection = false; // moves the player to the left
	private boolean rightDirection = false; // moves the player to the right
	private boolean fire = false; // checks to see if the player is firing a laser
	private boolean alienMoveRight = false;
	private boolean alienMoveLeft = false;
	private boolean playerAlive = true;
	private static int total;  // keeps track of the running total for the current wave
	private String modifier;
	private static Alien newAlien;
	
	private Image ship; // instantiates an image for the player ship
	private Image spaceBackground; // instantiates an image for the background
	private Image blackHole; // instantiates a .gif animation for when a black hole is formed
	private Image movingSpaceBackground; 
	private Image laser; // instantiates the laser image used
	private Image alienLaser; // instantiates the alien laser image used
	private Image explosion; // instantiates the explosion animation
	private Image transparent; 
	private Image bossLaser; // instantiates the boss laser animation
	private Image bossShip; // instantiates the boss ship image
	private Image bossExplosion; // instantiates the boss explosion animation
	private ImageIcon alienGif; 
	private Boss boss;  // instantiates the boss image used
	
	private File alienLaserSound;  // instantiates the alien laser sound
	private File playerLaserSound; // instantiates the player laser soun
	private File alienExplosionSound; // instantiates the alien explosion sound
	private File playerExplosionSound;  // instantiates the player explosion sound
	private File bossLaserSound; // instantiates the boss laser sound
	
	private static int alienMove; 
	private static int alienMove2;
	private static Alien alienAbove; // variable to hold a given alien that is found to be above an alien that is destroyed
	private int counter;
	private static int counter2;
	private int explosionTimer = 0; // timer to keep the explosion animation on the screen until it is over
	private int nextWaveTimer = 0;
	private int bossShot;
	private int time;
	private static int shots = 2000; // controls the speed at which the aliens fire at the player
	private static int wave = 1; // specifies the starting wave and changes as the player progresses through waves 
	private static boolean blackHoleActive = false;
	
	private static int numberOfAliens = 20;  // specifies the starting number of aliens in the game
	
	private ArrayList<ImageIcon> aliens = new ArrayList<ImageIcon>(); // ArrayList to hold active aliens in the game
	private ArrayList<Operator> operators = new ArrayList<Operator>(); // ArrayList to hold the operators used in the game
	private static int activeAliens = 20; // specifies the number of current active aliens in the game
	
	/*
	 * Timers used for various purposes within the game.
	 */
	
	private Timer timer = new Timer(500, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			nextWaveTimer++;
		}
			
	});
	
	private Timer bossShotTimer = new Timer(500, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			bossShot++;
		}
			
	});
	
	private Timer timer2 = new Timer(300, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			explosionTimer++;
		}
			
	});
	
	private Timer timer3 = new Timer(100, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			counter++;
		}
			
	});
	
	private Timer alienMovementTimer = new Timer(500, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			alienMove++;
		}
			
	});
	 
 	/*
 	 * This method instantiates a game board and continuously updates the state of the game
 	 */
	
	public Board() {
		initBoard(numberOfAliens);
		Timer timer = new Timer(20, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				time++;
				if (inGame) {
					if (leftDirection && playerAlive && ship_x >= 0) {
						ship_x -= 10;
					}
					if (rightDirection && playerAlive && ship_x <= boardWidth-75) {
						ship_x += 10;
					}
					if (fire && laser_y >= -20) {  
						laser_y -= 35;
					}
					if (fire && laser_y < -20) {
						fire = false;
						laser_y = boardHeight-60;
					}
					if (fire && laser_y >= 215 && laser_y <= 270 &&
							laser_x >= 425 && laser_x <= 535) {
						fire = false;
						laser_y = boardHeight-60;
						for (Operator o: operators) {
							o.cycle();
						}
					}
					
					for (ImageIcon a: aliens) {
						Random rand = new Random();
						int n = rand.nextInt(shots);
						Alien alien = ((Alien) a);
						if (counter2 < 640) {
							alien.moveDown3();
							counter2++;
						}
						if (alien.isShooting() == false && alien.isAlive() && time > 50) {
							if (n == 5) {
								play(alienLaserSound);
								alien.shoot();
							}
							if (boss.isAlive() && counter >= 90 && counter <= 100) {
								if (boss.getY() == 35) {
									boss.shoot();
									play(bossLaserSound);
								}
								counter = 0;
							}
						}
						if (alien.isShooting() && alien.getShot() <= boardHeight) {
							alien.shotDown();
						}
						if (alien.isShooting() && alien.getShot() > boardHeight) {
							alien.holdFire();
						}
						if (alien.getLocation() < 1) {
							alienMoveLeft = false;
							alienMoveRight = true;
						}
						if (alien.isAlive() && alien.getLocationY() > boardHeight - 50 && playerAlive) {
							play(playerExplosionSound);
							playerAlive = false;
							lives = 0;
							time = 0;
						}
						if (fire && alien.isAlive() && laser_y >= alien.getLocationY() - 15 && laser_y <= alien.getLocationY() + 64 &&
								laser_x >= alien.getLocation() - 20 && laser_x <= alien.getLocation() + 64) {
							if (shots > 500) {
								shots -= 10;
							}
							explosion_x = alien.getLocation();
							explosion_y = alien.getLocationY();
							play(alienExplosionSound);
							explode(alien);
							newAlien = new Alien("Resource/fs_enemy02A.png", alien.getLocation(), alien.getLocationY() - 390);
							activeAliens--;
							if (total == 0) {
								total = alien.getNumber();
							}
							else {
								for (Operator o: operators) {
									if (o.isActive()) {
										String type = o.getOperatorType();
										if (type == "+") {
											total = total + alien.getNumber();
											score += alien.getNumber() * 5;
										}
										if (type == "-") {
											total = total - alien.getNumber();
											score += alien.getNumber() * 5;
										}
										if (type == "x") {
											total = total * alien.getNumber();
											score += alien.getNumber() * 10;
										}
										if (type == "÷") {
											if (alien.getNumber() == 0) {
												blackHoleActive = true;
												inGame = false;
											}
											else {
												total = total / alien.getNumber();
												score += alien.getNumber() * 10;
											}
											
										}
										if (total < 0) {
											total = 0;
										}
										modifier = (type + " " + alien.getNumber());
									}
								}
							}
							if (boss.isAlive() && total == boss.getNumber()) {
								explosion_x = boardWidth/2 - 125;
								explosion_y = 0;
								play(alienExplosionSound);
								boss.explodeAlien();
								fire = false;
								score += boss.getNumber();
								total = 0;
							}
							
							score++;
							laser_y = boardHeight-60;
						}
						
						if (alien.isShooting() && alien.getShot() >= ship_y - 15 && alien.getShot() <= ship_y + 35 && alien.getShotLocation() >= ship_x - 45 &&
								alien.getShotLocation() <= ship_x + 45 && playerAlive) {
							play(playerExplosionSound);
							playerAlive = false;
							lives --;
							time = 0;
							alien.holdFire();
						}
						if (alien.isAlive() && ship_y >= alien.getLocationY() - 20 && ship_y <= alien.getLocationY() + 20 &&
								ship_x >= alien.getLocation() - 20 && ship_x <= alien.getLocation() + 20 && playerAlive) {
							play(playerExplosionSound);
							playerAlive = false;
							lives = 0;
							time = 0;
						}
						if (boss.isShooting() && ship_x >= 425 && ship_x <= 500 && playerAlive) {
							playerAlive = false;
							play(playerExplosionSound);
							lives --;
							time = 0;
						}
					}
					if (newAlien != null && activeAliens == 19) {
						alienMove = 0;
						alienMove2 = 0;
						for (ImageIcon a: aliens) {
							Alien alien = (Alien) a;
							if (alien.getLocationY() - 300 == newAlien.getLocationY() && alien.getLocation() == newAlien.getLocation()) {
								alienAbove = alien;
							}
						}
						aliens.add(newAlien);
						activeAliens++;
					}
					if (alienMove < 9 && alienAbove != null) {
						alienAbove.moveDown();
						alienMove++;
					}
					if (alienMove2 < 5 && newAlien != null) {
						newAlien.moveDown2();
						alienMove2++;
					}
					if (boss.getY() != 35) {
						boss.moveDown();
					}
					repaint();
				}
			}
		});
		timer.start();
	}
	
	private void initBoard(int a) {
		boss = new Boss("Resource/greeninvader.gif");
		timer3.start();
		for (int i = 0; i < a; i++) {
			aliens.add(new Alien("Resource/fs_enemy02A.png"));
		}
		for (int i = 0; i < 4; i++) {
			operators.add(new Operator());
		}
		for (Operator o: operators) {
			o.cycle();
			o.cycle();
		}
		addKeyListener(new Adapter());
		addMouseListener(new MouseInput());
		setBackground(Color.black);
		setFocusable(true);
		setDoubleBuffered(true);
		
		setPreferredSize(new Dimension(boardWidth, boardHeight));
		loadImages();
		loadSounds();
		repaint();
		
		
	}
	
	private void loadImages() {
		ImageIcon s = new ImageIcon("Resource/spaceship.png");
		ship = s.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		
		ImageIcon s1 = new ImageIcon("Resource/redLaser.png");
		laser = s1.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		
		ImageIcon s2 = new ImageIcon("Resource/laser.png");
		alienLaser = s2.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		
		ImageIcon s3 = new ImageIcon("Resource/spaceBackground.jpeg");
		spaceBackground = s3.getImage().getScaledInstance(boardWidth, boardHeight, Image.SCALE_DEFAULT);
		
		ImageIcon b = new ImageIcon("Resource/blackHole.gif");
		blackHole = b.getImage().getScaledInstance(boardWidth, boardHeight, Image.SCALE_DEFAULT);
		
		ImageIcon s4 = new ImageIcon("Resource/explode.gif");
		explosion = s4.getImage().getScaledInstance(125, 125, Image.SCALE_DEFAULT);
		
		ImageIcon s5 = new ImageIcon("Resource/transparent.png");
		transparent = s5.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		
		ImageIcon s6 = new ImageIcon("Resource/starryBackground.gif");
		movingSpaceBackground = s6.getImage().getScaledInstance(boardWidth, boardHeight, Image.SCALE_DEFAULT);
		
		ImageIcon s7 = new ImageIcon("Resource/bossLaser3.gif");
		bossLaser = s7.getImage().getScaledInstance(35, 850, Image.SCALE_DEFAULT);
		
		ImageIcon s8 = new ImageIcon("Resource/boss.png");
		bossShip = s8.getImage().getScaledInstance(280, 175, Image.SCALE_DEFAULT);
		
		ImageIcon s9 = new ImageIcon("Resource/explode.gif");
		bossExplosion = s9.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
		
		alienGif = new ImageIcon("Resource/greeninvader.gif");
		
	}
	
	private void loadSounds() {
		alienLaserSound = new File("Resource/alienLaser.wav");
		playerLaserSound = new File("Resource/playerLaser3.wav");
		alienExplosionSound = new File("Resource/explosionAlien.wav");
		playerExplosionSound = new File("Resource/explosion.wav");
		bossLaserSound = new File("Resource/Explosion+9.wav");
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
	    g2.scale(SCALE, SCALE);
	    g2.scale(INV_SCALE, INV_SCALE);
		draw(g);
	}
	
	private void draw(Graphics g) {
		if (inMenu) {
			menu(g);
		}
		else {
			
		if (inGame && aliens.size() > 0) {
			if (blackHoleActive) {
				g.drawImage(blackHole, 0, 0, this);
			}
			else {
				g.drawImage(spaceBackground, 0, 0, this);
			}

			String msg = "Lives: " + lives;
			String msg2 = "Score: " + score;
			String msg3 = "Total: " + total;
			Font small = new Font("Helvetica", Font.BOLD, 18);
			Font large = new Font("Helvetica", Font.BOLD, 35);
			
			g.setColor(Color.green);
			g.setFont(small);
			g.drawString(msg, 0, boardHeight-5);
			g.drawString(msg2, boardWidth-100, boardHeight-5);
			g.setFont(large);
			g.drawRect(480, 285, 35, 35);
			
			if (boss.isAlive()) {
				g.drawImage(bossShip, 358, boss.getY(), this);
				g.drawString(Integer.toString(boss.getNumber()), boardWidth/2 - 22, 80);
				if (boss.isShooting()) {
					bossShotTimer.start();
					if (bossShot < 3) {
						g.drawImage(bossLaser, 481, 186, this);
					}
					else {
						boss.holdFire();
						bossShotTimer.stop();
						bossShot = 0;
					}
				}
			}
			g.drawString(msg3, boardWidth/2 - 64, 355);
			if (boss.isAlive() == false && boss.inAfterLife() == false) {
				boss.holdFire();
				if (explosionTimer == 0) {
					g.drawImage(bossExplosion, explosion_x, explosion_y, this);
				}
				timer2.start();
				if (explosionTimer == 1) {
					timer2.stop();
					explosionTimer = 0;
					explosion.flush();
					boss.afterLife();
				}
			}
			if (boss.isAlive() == false && boss.inAfterLife() == true) {
				g.drawImage(transparent, explosion_x, explosion_y, this);
				nextWave(g);
			}
			
			if (playerAlive) {
				g.drawImage(ship, ship_x, ship_y, this);
			}
			else {
				if (explosionTimer == 0) {
					g.drawImage(explosion, ship_x, ship_y, this);
					timer2.start();
				}
				if (explosionTimer == 1) {
					g.drawImage(transparent, ship_x, ship_y, this);
					for (int i = 0; i < aliens.size(); i++) {
						Alien al = ((Alien) aliens.get(i));
						if (al.isShooting()) {
							al.holdFire();
						}
					}
				}
				if (explosionTimer == 2 && lives == 0) {
					inGame = false;
					timer2.stop();
					explosionTimer = 0;
					explosion.flush();
				}
				if (explosionTimer == 6 && lives > 0) {
					timer2.stop();
					explosionTimer = 0;
					explosion.flush();
					playerAlive = true;
				}
			}
			
			if (fire) {
				g.drawImage(laser, laser_x, laser_y, this);
			}
			
			for (Operator o: operators) {
				g.drawImage(o.getOperatorImage(), o.getOperatorX(), o.getOperatorY(), this);
			}
			
			for (int i = 0; i < aliens.size(); i++) {
				Alien al = ((Alien) aliens.get(i));
				if (al.isShooting()) {
					g.drawImage(alienLaser, al.getShotLocation() + 25, al.getShot() + 25, this);
				}
				if (al.isAlive()) {
					g.drawImage(al.getAlien(), al.getLocation(), al.getLocationY(), this);
					g.setColor(Color.GREEN);
					g.drawString(Integer.toString(al.getNumber()), al.getLocation() + 35, al.getLocationY() + 35);
				}
				if (al.isAlive() == false && al.inAfterLife() == false) {
					if (explosionTimer == 0) {
						g.drawImage(explosion, explosion_x, explosion_y, this);
						if (score > 1 && boss.isAlive()) {
							g.drawString(modifier, explosion_x, explosion_y + 53);
						}
					}
					timer2.start();
					if (explosionTimer == 1) {
						timer2.stop();
						explosionTimer = 0;
						explosion.flush();
						al.afterLife();
					}
				}
				if (al.isAlive() == false && al.inAfterLife() == true) {
					g.drawImage(transparent, explosion_x, explosion_y, this);
					if (al.isShooting() == false) {
						aliens.remove(al);
					}
				}
			}
			
			Toolkit.getDefaultToolkit().sync();
		}
		
		else {
			if (aliens.size() == 0) {
				//nextWave(g);
			}
			if (boss.isAlive() == false) {
				nextWave(g);
			}
			else {
				gameOver(g);
			}
		}
		}
	}
	
	private void explode(Alien a) {
		a.explodeAlien();
		fire = false;
	}
	
	public void menu(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g.drawImage(spaceBackground, 0, 0, this);
		Font font0 = new Font("arial", Font.BOLD, 100);
		Font font1 = new Font("arial", Font.BOLD, 60);
		g.setFont(font0);
		g.setColor(Color.green);
		g.setFont(font1);
		g.drawString("Play", playButton.x+40, playButton.y+70);
		g.drawString("Quit", quitButton.x+35, quitButton.y+70);
		g.setColor(Color.white);
		g2d.draw(playButton);
		g2d.draw(quitButton);
	}
	
	
	private void gameOver(Graphics g) {
		timer.start();
		if (nextWaveTimer < 14 && blackHoleActive) {
			g.drawImage(blackHole, 0, 0, this);
		}
		else {
			inGame = false;
			int waves = wave-2;
			String msg = "Game Over";
			String msg2 = "Score: " + score;
			String msg3 = "Waves Survived: " + waves;
			Font small = new Font("Helvetica", Font.BOLD, 50);
			
			g.setColor(Color.green);
			g.setFont(small);
			if (blackHoleActive) {
				String msg4 = "Game Over : Divided by zero!";
				g.drawString(msg4, boardWidth / 2 - 325, 250);
			}
			else {
				g.drawString(msg, boardWidth / 2-125, 250);
			}
			g.drawString(msg2, boardWidth / 2-100, 425);
			g.drawString(msg3, boardWidth / 2-200, 500);
		}
	}
	
	private void youWin(Graphics g) {
		int waves = wave-2;
		String msg1 = "You Win!";
		String msg2 = "Score: " + score;
		//String msg3 = "Waves Survived: " + waves;
		Font small = new Font("Helvetica", Font.BOLD, 50);
		
		g.drawImage(spaceBackground, 0, 0, this);
		g.setColor(Color.green);
		g.setFont(small);
		g.drawString(msg1, boardWidth / 2-125, 250);
		g.drawString(msg2, boardWidth / 2-120, 350);
		//g.drawString(msg3, boardWidth / 2-200, 500);
	}
	
	private void nextWave(Graphics g) {
		timer.start();
		
		if (nextWaveTimer == 2) {
			timer.stop();
			nextWaveTimer = 0;
			wave++;
			boss = new Boss("Resource/greeninvader.gif");
			boss.setAlienY(-100);
		}
	}
	
	private void displayWave(Graphics g) {
		g.drawImage(spaceBackground, 0, 0, this);
		String msg = "Wave " + wave;
		String msg2 = "Lives: " + lives;
		String msg3 = "Score: " + score;
		Font small = new Font("Helvetica", Font.BOLD, 18);
		Font large = new Font("Helvetica", Font.BOLD, 50);
		
		g.setColor(Color.green);
		g.setFont(large);
		g.drawString(msg, boardWidth / 2-90, 250);
		g.setFont(small);
		g.drawString(msg2, 0, boardHeight-5);
		g.drawString(msg3, boardWidth-100, boardHeight-5);
		if (playerAlive) {
			g.drawImage(ship, ship_x, ship_y, this);
		}
	}
	
	public void play(File f) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(f));
			clip.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	
	

	
	private class Adapter extends KeyAdapter {
		
		public void keyPressed(KeyEvent e) {
			if (playerAlive && inMenu == false) {
				int key = e.getKeyCode();
				
				if (key == KeyEvent.VK_LEFT) {
					rightDirection = false;
					leftDirection = true;
				}
				
				if (key == KeyEvent.VK_RIGHT) {
					leftDirection = false;
					rightDirection = true;
				}
				
				if (key == KeyEvent.VK_SPACE && fire == false) {
					play(playerLaserSound);
					fire = true;
					laser_x = ship_x+8;
				}
			}
			else if (inMenu == true) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_SPACE) {
					inMenu = false;
					displayWave(getGraphics());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					wave = 2;
					inGame = true;
					
				}
			}
		}
		
		public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				
				if (key == KeyEvent.VK_LEFT) {
					leftDirection = false;
				}
				
				if (key == KeyEvent.VK_RIGHT) {
					rightDirection = false;
				}
			
		}
	}
	
	private class MouseInput implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			//private Rectangle playButton = new Rectangle(200, 150, 100, 50);
			//private Rectangle quitButton = new Rectangle(200, 250, 100, 50);
			
			//private Rectangle playButton = new Rectangle(500, 150, 200, 100);
			//private Rectangle quitButton = new Rectangle(500, 350, 200, 100);
			
			int mx = e.getX();
			int my = e.getY();
			
			// Play button
			if (mx >= 500 && mx <= 700) {
				if (my >= 150 && my <= 250) {
					inMenu = false;
					displayWave(getGraphics());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					wave = 2;
					inGame = true;
				}
			}
			
			// Quit button
			if (mx >= 500 && mx <= 700) {
				if (my >= 350 && my <= 450) {
					System.exit(0);
				}
			}
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
