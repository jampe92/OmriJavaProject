package TheGameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import RangeAttacks.EnergyBall;
import sun.audio.*;



@SuppressWarnings("serial")
public class GameFrame extends JFrame{
	long lastAction = System.currentTimeMillis();
	private GamePanel gp;
	private ArrayList<Timer> allBallsTimer = new ArrayList<Timer>();
	private Timer jumpTimer;
	private boolean jumpTop = true; 
	AudioPlayer Ap ;
	AudioStream AS;
	AudioData	AD;
	ContinuousAudioDataStream loop = null;
	
	public GameFrame(int diff){
		this.setSize(750,580);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		gp = new GamePanel(diff);
		this.add(gp);	
//		BackgrundMusic();     player data not working
		this.addKeyListener(new GameKeyListener());
		this.setVisible(true);
	}
	
//	private void BackgrundMusic(){
//		ContinuousAudioDataStream loop = null;
//		try{
//		AS = new AudioStream(new FileInputStream("DBZ Sounds/BackgrundMusic.wav"));
//		AD = AS.getData();
//		loop = new ContinuousAudioDataStream(AD);
//		
//		}catch(Exception ex){
//			System.out.println(ex.getMessage());
//		}
//		Ap.start(loop);
//	}
	
	class GameKeyListener implements KeyListener {
		
		@Override
		public void keyPressed(KeyEvent ke) {
			gp.flagProblem = true;
				if(ke.getKeyCode() == KeyEvent.VK_W){
					if(gp.theGoku.getY() == 500){
						try{
							Ap = AudioPlayer.player;
							AS = new AudioStream(new FileInputStream("DBZ Sounds/jump.wav"));
							AD = AS.getData();
							loop = new ContinuousAudioDataStream(AD);
						}catch(Exception ex){
							System.out.println(ex.getMessage());
						}
						Ap.start(loop);
						jumpTimer = new Timer(15,new JumpTimerTask(loop));
						jumpTimer.start();
						gp.theGoku.setImageDefault();
						gp.repaint();
					}
				}// End Goku Jump
				if(ke.getKeyCode() == KeyEvent.VK_H){
					try {
						gp.theGoku.imageToReturn = ImageIO.read(new File("Animation/Hero/FightingMoves/1-BoxLeft.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				if(ke.getKeyCode() == KeyEvent.VK_D && ke.isControlDown()){
					if(gp.theGoku.getX()<500){
						try{
							gp.theGoku.setX(gp.theGoku.getX()+100);
							gp.theGoku.setImage(ke);
							gp.repaint();
//							AS = new AudioStream(new FileInputStream("DBZ Sounds/jump.wav"));
//							AD = AS.getData();
//							loop = new ContinuousAudioDataStream(AD);
						}catch(Exception ex){
							System.out.println(ex.getMessage());
						}
					}
				}
				if(ke.getKeyCode() == KeyEvent.VK_A && ke.isControlDown()){
					if(gp.theGoku.getX()>200){
						try{
							gp.theGoku.setX(gp.theGoku.getX()-100);
							gp.theGoku.setImage(ke);
							gp.repaint();
//							AS = new AudioStream(new FileInputStream("DBZ Sounds/jump.wav"));
//							AD = AS.getData();
//							loop = new ContinuousAudioDataStream(AD);
						}catch(Exception ex){
							System.out.println(ex.getMessage());
						}
					}
				}
				if(ke.getKeyCode() == KeyEvent.VK_G && (System.currentTimeMillis() -  lastAction)>500){
					try{
						Ap = AudioPlayer.player;
						AS = new AudioStream(new FileInputStream("DBZ Sounds/eyebeam_fire.wav"));
						AD = AS.getData();
					}catch(Exception ex){
						System.out.println(ex.getMessage());
					}
					lastAction = System.currentTimeMillis();
					gp.theGoku.dicreaseMana();
					gp.HeroBar.mb.dicreaseMana(5);
					Ap.start(AS);
					gp.allEnergyBall.add(new EnergyBall(gp.theGoku.getX()+2, gp.theGoku.getY()+19));
					allBallsTimer.add(new Timer(20,new BallTimerTask(ke,loop)));
					for(int i=0;i<allBallsTimer.size()||allBallsTimer.size()==0;i++){
						allBallsTimer.get(i).start();
					}
					gp.theGoku.setImageDefault();
					gp.repaint();
					
				}//End of Goku energy ball
				
				if(ke.getKeyCode() == KeyEvent.VK_Q  && (System.currentTimeMillis() -  lastAction)>500 ){
					if(gp.theGoku.getMana() > 90){
						gp.auraPressd = true;
						gp.repaint();
					}
				}
				else{
					try{
					gp.theGoku.setImage(ke);
					gp.repaint();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		}
		@Override
		public void keyReleased(KeyEvent ke) {
				gp.theGoku.setImageDefault();
				gp.repaint();
		}
		@Override
		public void keyTyped(KeyEvent arg0) {}
	}
	
	class BallTimerTask implements ActionListener{
		private KeyEvent ke;
		private ContinuousAudioDataStream loop;
		
		public BallTimerTask(KeyEvent ke,ContinuousAudioDataStream loop){
			this.ke = ke;
			this.loop = loop;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				gp.theGoku.setImage(ke);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			for(int i=0;i<gp.allEnergyBall.size();i++){
				gp.allEnergyBall.get(i).setX(gp.allEnergyBall.get(i).getX()+5);
				gp.repaint();
				if(gp.allEnergyBall.get(i).getX()>749){
					Ap.stop(this.loop);
					gp.allEnergyBall.remove(i);
					allBallsTimer.get(i).stop();
					allBallsTimer.remove(allBallsTimer.size()-1);
					gp.theGoku.setFireHandFlag(true);
					

				}
			}
		}
	}
	
	class JumpTimerTask implements ActionListener {
		ContinuousAudioDataStream loop;
		public JumpTimerTask(ContinuousAudioDataStream loop){
			this.loop = loop;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(jumpTimer.isRunning()){
				try {
					gp.theGoku.imageToReturn = ImageIO.read(new File("Animation/Hero/HeroPosiotion/7-Jump.png"));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				if(gp.theGoku.getY()<400 || gp.theGoku.getY()>500){
				gp.theGoku.setY(500);
				}
				if(jumpTop){
					gp.theGoku.setY(gp.theGoku.getY()-10);
					gp.repaint();
					if(gp.theGoku.getY()==400){
						jumpTop = false;
					}
				}else{
					gp.theGoku.setY(gp.theGoku.getY()+10);
					gp.repaint();
					if(gp.theGoku.getY()>499){
						jumpTimer.stop(); // stop the timer
						Ap.stop(this.loop);
						jumpTop = true;
					}
				}
			}
		}
	}

	
	
	
	
	
	
	
	
	
	
	
}
