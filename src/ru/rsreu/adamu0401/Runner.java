package ru.rsreu.adamu0401;

import ru.rsreu.adamu0401.Controller.GameMoves;
import ru.rsreu.adamu0401.Model.ComputerPlayer;
import ru.rsreu.adamu0401.Model.Path;
import ru.rsreu.adamu0401.View.MainView;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Runner {

	public static void main(String[] args) {
		Path path = new Path();
		GameMoves gameMoves = new GameMoves(path);
		MainView view = new MainView(gameMoves);
		Thread th = new Thread(view);
		th.start();
		ComputerPlayer computerPlayer = new ComputerPlayer(gameMoves);
		Thread th2 = new Thread(computerPlayer);
		th2.start();

	}

}
