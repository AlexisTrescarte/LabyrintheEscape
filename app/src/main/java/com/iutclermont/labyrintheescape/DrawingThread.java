package com.iutclermont.labyrintheescape;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

public class DrawingThread extends Thread {
    private GameView myGameView;
    private GameManager g;


    public DrawingThread(Context myG, int w,GameView myGameView){
        super();
        GameManager g = (GameManager) myG;
        this.g=g;
        this.myGameView=myGameView;
        myGameView.setItemImg(myG,w,g.getWallList());
        myGameView.setBackgroundColor(Color.GRAY);

    }

    @Override
    public void run() {
        while(!isInterrupted()){

            Log.d("test","thread");

            myGameView.postInvalidate();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {}

        }
    }
}
