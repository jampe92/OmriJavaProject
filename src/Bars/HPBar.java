package Bars;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HPBar extends JPanel{
	private int hp = 100;
	private int hpSize = 300;
	private JLabel hpIndicator;
	
	public HPBar(){
		this.hpIndicator = new JLabel(String.valueOf(hp)+"%");
		this.add(hpIndicator);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(0, 0, hpSize, 250);
		hpIndicator.setText(String.valueOf(hp)+"%");
	}
	public void dicreaseHp(){
		this.hpSize -= 30;
		this.hp -= 10;
	}

}
