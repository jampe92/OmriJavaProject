package HeroAndEnemy;

import java.awt.Image;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class NPC extends JPanel{
	private int x;
	private int y;
	private int health;

	public Image imageToReturn;

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
}
