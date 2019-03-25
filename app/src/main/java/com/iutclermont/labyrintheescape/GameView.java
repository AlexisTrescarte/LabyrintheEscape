package com.iutclermont.labyrintheescape;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.List;

public class GameView extends View implements SurfaceHolder.Callback{

    private Bitmap myPersImg;
    private Bitmap myWallImg;
    private Bitmap myBushImg;
    private Bitmap myArriveImg;
    private Bitmap myZombieImg;
    private GameManager gameManager;
    private List<Wall> listWall;
    DrawingThread myThread;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gameManager =(GameManager) context;
        myThread=new DrawingThread(context, gameManager.getWidth(),this);
        myThread.start();
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawBitmap(myPersImg, gameManager.getPersonnage().getX(), gameManager.getPersonnage().getY(),null);
        canvas.drawBitmap(myZombieImg, gameManager.getZombie().getX(), gameManager.getZombie().getY(),null);
        for (Wall wall:listWall) {
            switch (wall.getType()){
                case 'A' :
                    canvas.drawBitmap(myArriveImg,wall.getX(),wall.getY(),null);break;
                case 'B' :
                    canvas.drawBitmap(myBushImg,wall.getX(),wall.getY(),null);break;
                case 'W' :
                    canvas.drawBitmap(myWallImg,wall.getX(),wall.getY(),null);break;

            }
        }

    }

    public void setItemImg(Context c , int Width, List<Wall> listWall){
        int size=Width/16;
        //pour le personnage
        Resources res = c.getResources();
        Bitmap bitmapP = BitmapFactory.decodeResource(res,R.mipmap.perso);
        this.myPersImg=Bitmap.createScaledBitmap(bitmapP , size, size, false);

        //pour les murs
        Bitmap bitmapW = BitmapFactory.decodeResource(res,R.mipmap.wall2);
        this.myWallImg=Bitmap.createScaledBitmap(bitmapW , size, size, false);

        //pour l'arrivé
        Bitmap bitmapA = BitmapFactory.decodeResource(res,R.mipmap.arrive);
        this.myArriveImg=Bitmap.createScaledBitmap(bitmapA , size, size, false);

        //pour les buisson
        Bitmap bitmapB = BitmapFactory.decodeResource(res,R.mipmap.bush);
        this.myBushImg=Bitmap.createScaledBitmap(bitmapB , size, size, false);

        //pour les buisson
        Bitmap bitmapZ = BitmapFactory.decodeResource(res,R.mipmap.zombie);
        this.myZombieImg=Bitmap.createScaledBitmap(bitmapZ , size, size, false);

        this.listWall=listWall;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }


}
