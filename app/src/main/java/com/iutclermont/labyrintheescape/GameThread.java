package com.iutclermont.labyrintheescape;

import java.util.Random;

public class GameThread extends Thread {

    private Boolean running = false;
    private GameManager myGameManager;

    public GameThread(GameManager myG){
        this.myGameManager =myG;
    }

    @Override
    public void run() {
        while(running){
            Random rand = new Random();

            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            myGameManager.moveZombie(rand.nextInt(4));

        }
    }

    public void setRunning(Boolean b ){
        this.running=b;
    }
}
