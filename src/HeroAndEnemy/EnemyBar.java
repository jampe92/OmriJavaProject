package HeroAndEnemy;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Bars.ControlPanel;
import Bars.HPBar;

public class EnemyBar extends NPC  implements Dagemable{
	private int x;
	private int y;
	private int health;
	private HPBar enemyBar;

	public Image imageToReturn;

	public EnemyBar(int Difecality, int x, int y) {
		this.setLayout(new FlowLayout());
		
		try {
			imageToReturn = ImageIO.read(new File("Animation/Enemys/Bardock(ENEMY)/Stand/Standing.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		if (Difecality == 0) { // 0 means Kallll
			setX(x);
			setY(y);
			this.health = 100;
		} else {
			setX(x);
			setY(y);
			this.health = 150;
		}
	}


	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void AddOrDecHealth(int health) {
		this.health += health;
	}

	public int getHealth() {
		return this.health;
	}
	
	public Image setImage(KeyEvent ke) throws IOException{
		int newX = getX();
		return imageToReturn;
	}
	
	public void setImageDefault(){
		try {
			this.imageToReturn = ImageIO.read(new File("Animation/Enemys/Bardock(ENEMY)/Stand/Standing.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		};
	}
	
	public Image getImage(){
		return imageToReturn;
	}


	@Override
	public void dicreaseHp(ControlPanel cp) {
		this.health -= 10;
		System.out.println(health);
	}


	@Override
	public void increaseHP() {
		this.health += 10;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}