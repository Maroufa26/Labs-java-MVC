package ru.rsreu.adamu0401.Model;

import ru.rsreu.adamu0401.View.Pawn;

public class Player {
	int height,width,status;
	public int coin;
	public Pawn[] pa=new Pawn[4];
	public Player(int height,int width) {
		status=-1;
		coin=0;
		for(int i=0;i<4;i++) {
			pa[i]=new Pawn(height,width);
		}
	}
}
