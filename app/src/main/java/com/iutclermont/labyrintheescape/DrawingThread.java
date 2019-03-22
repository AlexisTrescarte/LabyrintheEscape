package com.iutclermont.labyrintheescape;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

import java.util.List;
import java.util.Random;

public class DrawingThread extends Thread {
    private Gameplay myGameplay;
    private myCanvas surface;
    private Canvas canvas;


    public DrawingThread(Context myG, int w, List<Wall> lW ,Personage perso, Personage zombie){

        canvas = new Canvas();
        surface = new myCanvas(myG);
        surface.setItemImg(myG,w,lW);
        surface.setBackgroundColor(Color.GRAY);
        surface.setPers(perso,zombie);

        Gameplay g = (Gameplay) myG;
        g.setContentView(surface);
    }

    @Override
    public void run() {
        surface.draw(canvas);
    }
}
