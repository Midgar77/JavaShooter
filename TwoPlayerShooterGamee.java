import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class TwoPlayerShooterGamee extends JPanel{
	int WINDOW_WIDTH = 1300;
	int WINDOW_HEIGHT = 720;
	int playerScore = 0;
	int bossScore = 0;

	Boss boss = new Boss(this);
	Player player = new Player(this);
	ArrayList<Bullet> playerBulletList = new ArrayList<Bullet>();
	ArrayList<Bullet> bossBulletList = new ArrayList<Bullet>();
	

	public TwoPlayerShooterGamee(){


		addKeyListener(new KeyListener() {
			//boolean activeKey = true;  //activeKey set up so can only move in one direction at a time

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				player.keyReleased(e);
				boss.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				player.keyPressed(e);
				boss.keyPressed(e);
			}
		});
		setFocusable(true);

	}

	public void addPlayerBullet(Bullet bullet){
		playerBulletList.add(bullet);
	}

	public void addBossBullet(Bullet bullet){
		bossBulletList.add(bullet);
	}
	
	private void move() {
		
		if(playerScore >= 20)
			playerWon();
		else if(bossScore >= 20)
			bossWon();
		
		player.move();
		boss.move();

		for(int i = 0; i < playerBulletList.size(); i++){
			playerBulletList.get(i).move();
		}

		for(int i = 0; i < bossBulletList.size(); i++){
			bossBulletList.get(i).move();
		}
	}
	
	public void playerWon(){
		JOptionPane.showMessageDialog(null, player.getName()+" has won!", "Game Over!", JOptionPane.YES_NO_OPTION);
		System.exit(0);
	}
	
	public void bossWon(){
		JOptionPane.showMessageDialog(null, boss.getName()+" has won!", "Game Over!", JOptionPane.YES_NO_OPTION);
		System.exit(0);
	}

	public void resetGame(){
		player.reset();
		boss.reset();
	}

	public void addOneToScore(){
		playerScore += 1;
	}
        
        public void getPlayerNames()
        {
            String player1Name = JOptionPane.showInputDialog("Enter The Name of First Player!");
            player.setName(player1Name);
            
            String player2Name = JOptionPane.showInputDialog("Enter The Name of Second Player!");
            boss.setName(player2Name);
        }

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		this.setBackground(Color.WHITE);
		
		g2d.setColor(Color.ORANGE);
		g2d.fillRect(3, WINDOW_HEIGHT/2 - 10, WINDOW_WIDTH, 20);

		player.paint(g2d);
		boss.paint(g2d);
		for(int i = 0; i < playerBulletList.size(); i++){
			playerBulletList.get(i).paint(g2d);
		}

		for(int i = 0; i < bossBulletList.size(); i++){
			bossBulletList.get(i).paint(g2d);
		}



		g2d.setColor(Color.GRAY);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
                g2d.drawString(String.valueOf(boss.getName()), 10, 30);
		g2d.drawString(String.valueOf(bossScore), 25, 55);
		
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
                g2d.drawString(String.valueOf(player.getName()), 10, WINDOW_HEIGHT - 50);
		g2d.drawString(String.valueOf(playerScore), 25, WINDOW_HEIGHT - 25);
	}




	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Shooter");
		TwoPlayerShooterGamee game = new TwoPlayerShooterGamee();
		frame.add(game);
		frame.setSize(game.WINDOW_WIDTH, game.WINDOW_HEIGHT);
		frame.setVisible(true);
		game.getPlayerNames();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while (true) {
                        
			game.move();
			game.repaint();
                        
			Thread.sleep(10);
		}
                
	}
}
