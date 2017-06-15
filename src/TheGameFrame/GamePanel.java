package TheGameFrame;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import Bars.ControlPanel;
import HeroAndEnemy.EnemyBar;
import HeroAndEnemy.HeroGoku;
import HeroAndEnemy.NPC;
import RangeAttacks.EnergyBall;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	ControlPanel HeroBar;
	JPanel gokuPanel;
	Image Stage;
	HeroGoku theGoku;
	Image GokuImage;
	EnemyBar enemy;
	private boolean timerIsRunning = false;
	boolean auraPressd = false;
	public ArrayList<EnergyBall> allEnergyBall = new ArrayList<EnergyBall>();
	public boolean flagProblem = true;
	private Timer aura;

	public GamePanel(int diff) {
		HeroBar = new ControlPanel();
		this.setLayout(new FlowLayout());
		Dimension dim = new Dimension(300, 60);
		try {
			Stage = ImageIO.read(new File("Animation/Stages/StageOne.png"));
		} catch (IOException e) {
			System.out.println("Canot find the image");
		}
		theGoku = new HeroGoku(diff, 50, 500);
		enemy = new EnemyBar(diff,700,500);
		HeroBar.setPreferredSize(dim);
		this.add(HeroBar);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Stage, 0, 0, this);
		g.drawImage(theGoku.getImage(), theGoku.getX(), theGoku.getY(), this);
		g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
		if(auraPressd){
			if(HeroBar.getMana() <= 100){
				g.drawImage(theGoku.setAuraImage(), theGoku.getX()-20, theGoku.getY()-10, this);
				System.out.println("bla");
				HeroBar.mb.increaseMana(1);
			}
			
			auraPressd = false;
		}
		if(allEnergyBall.size()>0){
			for(int i=0;i<allEnergyBall.size()&& flagProblem == true;i++){
				g.drawImage(allEnergyBall.get(i).imageToReturn, allEnergyBall.get(i).getX(), allEnergyBall.get(i).getY(), this);
				if(checkIfHit(theGoku,allEnergyBall.get(i).getX(),allEnergyBall.get(i).getY())){
					theGoku.dicreaseHp(HeroBar);
//					HeroBar.hb.dicreaseHp();
					flagProblem = false;
				}else{
					if(checkIfHit(enemy,allEnergyBall.get(i).getX(),allEnergyBall.get(i).getY())){
						enemy.dicreaseHp(HeroBar); // Change it to ENEMY BAR!!
						theGoku.dicreaseHp(HeroBar);   ////Just for testing!!
						flagProblem = false;
					}
				}
			}
		}
	}

	class gokuTimerTask extends TimerTask {
		@Override
		public void run() {
			repaint();
		}
	}
	public boolean checkIfHit(NPC npc,int BallX, int BallY){
		if((((BallY > npc.getY()&&(BallY < npc.getY()+50)))&&((BallX > npc.getX())&&(BallX < npc.getX()+50)))&& (npc instanceof EnemyBar)){
			flagProblem = false;
			return true;
		}
		return false;
	}
	


}
