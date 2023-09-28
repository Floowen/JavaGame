package main;

import javax.swing.JFrame;

public class GameWindow{
	private JFrame jframe;
	
	//constructor
	public GameWindow(GamePanel gamePanel) {
		jframe = new JFrame();
		
		jframe.setSize(400, 400); //width,height (px)
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
		jframe.add(gamePanel);
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true); //always set on end
	}
}
