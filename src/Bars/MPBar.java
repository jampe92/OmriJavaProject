package Bars;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MPBar extends JPanel{
	private int mana = 100;
	private int manaSize = 300;
	private JLabel manaIndicator;
	
	public MPBar(){
		this.manaIndicator = new JLabel(String.valueOf(mana)+"%");
		this.add(manaIndicator);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, manaSize, 250);
		manaIndicator.setText(String.valueOf(mana)+"%");
		
	}
	public void dicreaseMana(int mana){
		this.manaSize -= mana*3;
		this.mana -= mana;
	}
	public void increaseMana(int mana){
		this.manaSize += mana*3;
		this.mana += mana;
	}
	public int getMana() {
		return mana;
	}

}
