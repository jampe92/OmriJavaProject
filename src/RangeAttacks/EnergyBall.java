package RangeAttacks;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class EnergyBall {
	private int x;
	private int y;
	public Image imageToReturn;
	
	public EnergyBall(int x,int y){
		try {
			imageToReturn = ImageIO.read(new File("Animation/Hero/SpacialMoves(Balls)/3-FireBall.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		setX(x);
		setY(y);
	}
	
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public void moveBallForward(){
		this.setX(this.getX()+10);
	}
	public Image getImage(){
		return this.imageToReturn;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
