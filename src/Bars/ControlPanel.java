package Bars;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel {

	public HPBar hb;
	public MPBar mb;

	public ControlPanel() {
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		hb = new HPBar();
		mb= new MPBar();
		this.setLayout(new BorderLayout());
		this.add(hb,BorderLayout.NORTH);
		this.add(mb,BorderLayout.SOUTH);
	}

	public void dicreaseHp() {
		hb.dicreaseHp();
		this.repaint();

	}

	public int getMana() {
		return mb.getMana();
	}
		

}
