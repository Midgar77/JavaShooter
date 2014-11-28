import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Player extends Character{


	private TwoPlayerShooterGamee game;
	public final int WIDTH = 60;
	public final int HEIGHT = 60;
	int x = 0;
	int xa = 0;
	int y = 0;
	int ya = 0;
	int speed = 3;
	File imgSource1 = new File("res/buckysFront.png");
	File imgSource2 = new File("res/buckysBack.png");
	File imgSource3 = new File("res/buckysLeft.png");
	File imgSource4 = new File("res/buckysRight.png");
	File imgSource5 = new File("res/ShooterBossHappy.png");
	BufferedImage player;
	boolean activeKey = true;
	boolean speedBoost = false;



	public Player(TwoPlayerShooterGamee game) {
		try {
			player = ImageIO.read(this.getClass().getResource("/res/buckysFront.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.game = game;
		y = game.WINDOW_HEIGHT - 175;
		x = game.WINDOW_WIDTH / 2 - (WIDTH / 2);


	}

	void move() {
		
		if(game.playerScore % 2 == 0 && game.playerScore != 0 && !speedBoost){
			speed++;
			speedBoost = true;
		}
		
		if (x + xa > 0 && x + xa < game.getWidth()-WIDTH)
			x = x + xa;

		if (y + ya > game.getHeight()/2 && y + ya < game.getHeight()-HEIGHT)
			y = y + ya;
	}

	public void paint(Graphics2D g) {
		g.drawImage(player, x, y, WIDTH, HEIGHT, game);
	}

	public void keyReleased(KeyEvent e) {

		xa = 0;
		ya = 0;
		activeKey = true;
		
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT && activeKey)
			turnLeft();
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && activeKey)
			turnRight();
		if (e.getKeyCode() == KeyEvent.VK_UP && activeKey)
			turnUp();
		if (e.getKeyCode() == KeyEvent.VK_DOWN && activeKey)
			turnDown();
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			shoot();
		
		
	}

	 void shoot(){
		if(game.playerBulletList.size() >= 5){
			game.playerBulletList.remove(0);
		}
		Bullet bullet = new Bullet(game, this);
		bullet.x = x + WIDTH/2;
		bullet.y = y - bullet.HEIGHT;

	}

	 void turnLeft(){
		xa = -speed;
		try {
			player = ImageIO.read(this.getClass().getResource("/res/buckysLeft.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		activeKey = false;
	
	}

	 void turnRight(){
		xa = speed;
		try {
			player = ImageIO.read(this.getClass().getResource("/res/buckysRight.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		activeKey = false;
		

	}

	 void turnUp(){
		ya = -speed;
		try {
			player = ImageIO.read(this.getClass().getResource("/res/buckysBack.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		activeKey = false;
		
		
	}

	 void turnDown(){
		ya = speed;
		try {
			player = ImageIO.read(this.getClass().getResource("/res/buckysFront.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		activeKey = false;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	public void shot(Bullet bullet){
		bullet.delete();
		game.bossScore++;
		game.boss.speedBoost = false;

		try {
			game.boss.boss = ImageIO.read(this.getClass().getResource("/res/ShooterBossHappy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		game.boss.count = 0;
	}

	public void reset(){
		y = game.WINDOW_HEIGHT - 75;
		x = game.WINDOW_WIDTH / 2 - (WIDTH / 2);
		xa = 0;
		ya = 0;
		speed = 1;
	}


}
