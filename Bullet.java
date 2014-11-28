import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet {


	private TwoPlayerShooterGamee game;
	private Character shooter;
	public final int WIDTH = 10;
	public final int HEIGHT = 50;
	int x = 0;
	int xa = 0;
	int y = 0;
	int ya = 0;
	int speed = 3;
	File imgSource1 = new File("res/Bullet.png");
	File imgSource2 = new File("res/BossBullet.png");
	BufferedImage bullet;
	boolean playerBullet = false;


	public Bullet(TwoPlayerShooterGamee game, Character shooter) {
		this.game = game;
		this.shooter = shooter;
		
		if(shooter.getClass().equals(new Player(game).getClass())){
			playerBullet = true;
			try {
				bullet = ImageIO.read(this.getClass().getResource("/res/Bullet.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			game.addPlayerBullet(this);
		}
		else{
			playerBullet = false;
			try {
				bullet = ImageIO.read(this.getClass().getResource("/res/BossBullet.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			game.addBossBullet(this);
		}
		
	}

	void move() {
		//	x = x + xa;

		if(!collision()){
			if(playerBullet)
				y = y - speed;
			else
				y = y + speed;
		}
		
		//delete bullets that went off map
		if(y < 0 - HEIGHT || y > game.WINDOW_HEIGHT)
			delete();
	}

	public void delete(){
		game.playerBulletList.remove(this);
		game.bossBulletList.remove(this);
	}

	private boolean collision() {
		if(game.player.getBounds().intersects(getBounds())){
			game.player.shot(this);
			return true;
		}
		else if(game.boss.getBounds().intersects(getBounds())){
			game.boss.shot(this);
			return true;
		}
		else
			return false;
	}

	public void paint(Graphics2D g) {
		g.drawImage(bullet, x, y, WIDTH, HEIGHT, game);
	}


	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	public void reset(){
		y = game.WINDOW_HEIGHT - 75;
		x = game.WINDOW_WIDTH / 2 - (WIDTH / 2);
		xa = 0;
		ya = 0;
		speed = 1;
	}

}
