package HeroAndEnemy;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Bars.ControlPanel;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class HeroGoku extends NPC implements Dagemable{
	private boolean fireHandFlag = true;
	private int x;
	private int y;
	private int health;
	private int gold;
	private double mana;
	public Image imageToReturn;
	public 	Image auraImage;
	public static int auraChange = 0;
	AudioPlayer Ap = AudioPlayer.player;
	AudioStream AS;
	AudioData	AD;
	ControlPanel cp;
	
	public HeroGoku(int Difecality, int x, int y) {
		try {
			imageToReturn = ImageIO.read(new File("Animation/Hero/HeroPosiotion/1 - standingStill.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		if (Difecality == 0) { // 0 means Kallll
			setX(x);
			setY(y);
			this.health = 150;
			this.gold = 1000;
			this.mana = 150;
		} else {
			setX(x);
			setY(y);
			this.health = 100;
			this.gold = 500;
			this.mana = 50;
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

	public void AddOrDecGold(int gold) {
		this.gold += gold;
	}

	public void AddOrDecMana(double d) {
		this.mana += d;
	}

	public int getHealth() {
		return this.health;
	}

	public int getGold() {
		return this.gold;
	}

	public double getMana() {
		return this.mana;
	}
	
	public Image setImage(KeyEvent ke) throws IOException{
		int newX = getX();
		try{
			if(ke.getKeyCode() == KeyEvent.VK_A){
				imageToReturn = ImageIO.read(new File("Animation/Hero/HeroPosiotion/4 - FlghtBackward.png"));
				setX(newX-20);
				return imageToReturn;
			}
			if(ke.getKeyCode() == KeyEvent.VK_D){
				imageToReturn = ImageIO.read(new File("Animation/Hero/HeroPosiotion/3 - FlghtFoward.png"));
				setX(newX+20);
				return imageToReturn;
			}
			if(ke.getKeyCode() == KeyEvent.VK_D && ke.isControlDown()){
				
				imageToReturn = ImageIO.read(new File("Animation/Hero/HeroPosiotion/5 - TeleportingFor&back.png"));
				return imageToReturn;
			}
			if(ke.getKeyCode() == KeyEvent.VK_A && ke.isControlDown()){
				
				imageToReturn = ImageIO.read(new File("Animation/Hero/HeroPosiotion/5 - TeleportingFor&back.png"));
				return imageToReturn;
			}
//			if(ke.getKeyCode() == KeyEvent.VK_H){
//				imageToReturn = ImageIO.read(new File("Animation/Hero/FightingMoves/1-BoxLeft.png"));
//				return imageToReturn;
//			}
			if(ke.getKeyCode() == KeyEvent.VK_E){
				imageToReturn = ImageIO.read(new File("Animation/Hero/FightingMoves/9-DeffaceTwoHands.png"));
				return imageToReturn;
			}
			if(ke.getKeyCode() == KeyEvent.VK_Y){
				imageToReturn = ImageIO.read(new File("Animation/Hero/FightingMoves/2-BoxRight.png"));
				return imageToReturn;
			}
			if(ke.getKeyCode() == KeyEvent.VK_G){
				if(fireHandFlag){
					imageToReturn = ImageIO.read(new File("Animation/Hero/SpacialMoves(Balls)/1-FireBallFromLeftHand.png"));
					fireHandFlag = false;
					return imageToReturn;
				}else{
					imageToReturn = ImageIO.read(new File("Animation/Hero/HeroPosiotion/1 - standingStill.png"));
					return imageToReturn;
				}
			}

		}catch(Exception fnf){
			System.out.println("Fill not found");
			System.exit(0);
		}
		
		imageToReturn = ImageIO.read(new File("Animation/Hero/HeroPosiotion/1 - standingStill.png"));
				return imageToReturn;
	}
	
	public void setImageDefault(){
		try {
			this.imageToReturn = ImageIO.read(new File("Animation/Hero/HeroPosiotion/1 - standingStill.png"));
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
		health -= 10;
		cp.hb.dicreaseHp();
	}

	public void dicreaseMana() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void increaseHP() {
		// TODO Auto-generated method stub
		
	}

	public void increaseMana(ControlPanel cp) {
		
		
	}

	public void setFireHandFlag(boolean b) {
		this.fireHandFlag = b;	
	}

	public Image setAuraImage() {
		
		try{
			if(auraChange == 0){
				auraImage = ImageIO.read(new File("Animation/Hero/HeroSuperSaiyanMod/5-Aura(1).png"));
				auraChange = 1;
				return auraImage;
			}else{
				if(auraChange == 1){
					auraImage = ImageIO.read(new File("Animation/Hero/HeroSuperSaiyanMod/6-Aura(2).png"));
					auraChange = 2;
					return auraImage;
				}else{
					auraImage = ImageIO.read(new File("Animation/Hero/HeroSuperSaiyanMod/7-Aura(3).png"));
					auraChange = 0;
					return auraImage;
				}
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return auraImage;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
