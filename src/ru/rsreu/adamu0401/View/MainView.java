package ru.rsreu.adamu0401.View;

import javax.swing.JFrame;

import ru.rsreu.adamu0401.Controller.GameMoves;

public class MainView implements Runnable{
	private GameMoves gm;
	public MainView (GameMoves gm) {
		this.gm = gm;
	}
	public void showView() {
		JFrame jframe = new JFrame();
	    jframe.setBounds(10,10,1000,600);
	    jframe.setTitle("LUDO");
	    jframe.setResizable(false);
	    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    gm.setFocusable(true);
	    gm.addKeyListener(gm);
	    gm.addMouseListener(gm);
	    jframe.add(gm);
	    jframe.setVisible(true);
	}
	@Override
	public void run() {
		try {
			showView();
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		
	}
}
