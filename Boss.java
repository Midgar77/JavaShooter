import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


public class Boss extends Character{


	private TwoPlayerShooterGamee game;
	public final int WIDTH = 80;
	public final int HEIGHT = 60;
	int x = 0;
	int xa = 0;
	int y = 0;
	int ya = 0;
	int speed = 3;
	File imgSource1 = new File("res/ShooterBoss.png");
	File imgSource2 = new File("res/ShooterBossHurt.png");
	File imgSource3 = new File("res/ShooterBossHappy.png");
	BufferedImage boss;
	int count = 0;
	boolean activeKey = true;
	boolean speedBoost = true;



	public Boss(TwoPlayerShooterGamee game) {
		try {
			boss = ImageIO.read(this.getClass().getResource("/res/ShooterBoss.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.game = game;
		y = 50;
		x = game.WINDOW_WIDTH / 2 - (WIDTH / 2);


	}

	void move() {

		if(game.bossScore % 2 == 0 && game.bossScore != 0 && !speedBoost){
			speed++;
			speedBoost = true;
		}

		if (x + xa > 0 && x + xa < game.getWidth()-WIDTH)
			x = x + xa;

		if (y + ya > 0 && y + ya < game.getHeight()/2-HEIGHT)
			y = y + ya;

		if(count == 50){
			try {
				boss = ImageIO.read(this.getClass().getResource("/res/ShooterBoss.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			count = 10;
		}
		else
			count++;
	}

	public void keyReleased(KeyEvent e) {
		xa = 0;
		ya = 0;
		activeKey = true;
		
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A && activeKey)
			turnLeft();
		if (e.getKeyCode() == KeyEvent.VK_D && activeKey)
			turnRight();
		if (e.getKeyCode() == KeyEvent.VK_W && activeKey)
			turnUp();
		if (e.getKeyCode() == KeyEvent.VK_S && activeKey)
			turnDown();
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			shoot();
		
		
	}

	 void turnLeft(){
		xa = -speed;
		activeKey = false;
	}

	 void turnRight(){
		xa = speed;
		activeKey = false;
	}

	 void turnUp(){
		ya = -speed;
		activeKey = false;
	}

	void turnDown(){
		ya = speed;
		activeKey = false;
	}

	 void shoot(){
		if(game.bossBulletList.size() >= 5){
			game.bossBulletList.remove(0);
		}
		Bullet bullet = new Bullet(game, this);
		bullet.x = x + WIDTH/2;
		bullet.y = y + bullet.HEIGHT + HEIGHT;
	}

	public void paint(Graphics2D g) {
		g.drawImage(boss, x, y, WIDTH, HEIGHT, game);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	public void shot(Bullet bullet){
		bullet.delete();
		game.playerScore++;
		game.player.speedBoost = false;
		try {
			boss = ImageIO.read(this.getClass().getResource("/res/ShooterBossHurt.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		count = 0;
	}

	public void reset(){
		y = game.WINDOW_HEIGHT - 75;
		x = game.WINDOW_WIDTH / 2 - (WIDTH / 2);
		xa = 0;
		ya = 0;
		speed = 1;
	}

}
