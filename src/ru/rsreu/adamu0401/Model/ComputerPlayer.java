package ru.rsreu.adamu0401.Model;

import ru.rsreu.adamu0401.Controller.GameMoves;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ComputerPlayer implements Runnable {
    private final GameMoves gameMoves;
    private final Button button;

    public ComputerPlayer(GameMoves gameMoves) {
        this.gameMoves = gameMoves;
        this.button = new Button("Enter");

    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            simulateEnterClick();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void simulateEnterClick() throws InterruptedException {
        if (this.gameMoves.getCurrent_player() != 0) {
            KeyEvent e = new KeyEvent(this.button, 1, 20, 1, 10, 'E');
            this.gameMoves.keyPressed(e);
            run();
        } else {
            Thread.sleep(10 * 200);
            simulateEnterClick();
        }

    }
}
