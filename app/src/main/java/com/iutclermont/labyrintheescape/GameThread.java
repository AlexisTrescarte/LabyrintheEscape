package com.iutclermont.labyrintheescape;

import android.util.Log;

import java.util.Random;

public class GameThread extends Thread {

    private Boolean running = false;
    private Gameplay myGameplay;

    public GameThread(Gameplay myG){
        this.myGameplay=myG;
    }

    @Override
    public void run() {
        while(running){
            Random rand = new Random();

            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            myGameplay.moveZombie(rand.nextInt(4));

        }
    }

    public void setRunning(Boolean b ){
        this.running=b;
    }
}
