package ru.rsreu.adamu0401;

import ru.rsreu.adamu0401.Controller.GameMoves;
import ru.rsreu.adamu0401.Model.Path;
import ru.rsreu.adamu0401.View.MainView;

public class Runner {

	public static void main(String[] args) {
		Path path = new Path();
		GameMoves gameMoves = new GameMoves(path);
		MainView view = new MainView(gameMoves);
		Thread th = new Thread(view);
		th.start();
	}

}
