package com.iutclermont.labyrintheescape;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;

import java.io.IOException;
import java.util.List;

import static java.lang.Math.abs;

public class Gameplay extends AppCompatActivity {

    private Personage personnage;
    public Personage zombie;
    private myCanvas myCanvas;
    private SensorManager mSensorManager = null;
    private Sensor mAccelerometer = null;
    private List<Wall> wallList;
    private FileReader reader;
    private DrawingThread drawingThread;
    private int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //avoir la taille de l'écran
        Display ecran = getWindowManager().getDefaultDisplay();
        final int Width= ecran.getWidth();
        //

        personnage=new Personage(Width/16,0,0);
        zombie=new Personage(Width/16,Width-Width/16,8*Width/9);


        //On récupére les item du niveau
        reader=new FileReader(1);
        try {
            wallList = reader.getWall(Width,this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        drawingThread=new DrawingThread(this,Width,wallList,personnage,zombie);
        //met en place la surface


        //taille des objet
        size = Width/16;

        //on gére l'accelerométre
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorEventListener mSensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                // La valeur sur l'axe x
                float y = event.values[0];
                // La valeur sur l'axe y
                float x = event.values[1];

                Boolean collision=true;
                float potentialX=0;
                float potentialY=0;

                if((abs(x)>4 || abs(y)>4) && personnage.getCanMove() ){

                    personnage.setCanMove(false);
                    if(abs(x)>abs(y)){
                        if(x>0){
                            potentialX= personnage.getX()+size/2;
                        }
                        else{
                            potentialX= personnage.getX()-size/2;
                        }
                         potentialY=personnage.getY();
                    }
                    else{
                        potentialX=personnage.getX();
                        if(y>0){
                            potentialY= personnage.getY()+size/2;
                        }
                        else{
                            potentialY= personnage.getY()-size/2;
                        }
                    }

                    // Puis on modifie les coordonnées en fonction de la vitesse
                    personnage.setVisible(true);
                    collision=false;

                    //vérification de la position dans l'espace de jeux
                    if(potentialX <0){
                        potentialX=0;
                    }
                    if(potentialX>Width-size){
                        potentialX=Width-size;
                    }
                    if(potentialY<0){
                        potentialY=0;
                    }
                    if(potentialY>9*size-size){
                        potentialY=9*size-size;
                    }
                    //collision avec les murs
                    for (Wall wall:wallList) {

                        if((potentialX==wall.getX() || potentialX==wall.getX()+size/2 || potentialX+size/2==wall.getX() || potentialX+size/2==wall.getX()+size/2)&&( potentialY==wall.getY() || potentialY==wall.getY()+size/2 || potentialY+size/2==wall.getY() || potentialY+size/2==wall.getY()+size/2)){
                            switch (wall.getType()){
                                case 'W' :
                                    collision=true;
                                    break;
                                case 'B':
                                    personnage.setVisible(false);
                                    break;
                                case 'A':
                                    //stauper le mouvement !
                                    mSensorManager.unregisterListener(this);
                                    onWin();
                                    break;
                            }
                        }
                    }
                }

                if(collision==false){
                    personnage.setX(potentialX);
                    personnage.setY(potentialY);

                    setContentView(myCanvas);
                }

                personnage.setCanMove(true);

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }

        };
        mSensorManager.registerListener(mSensorEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        GameThread gameLoop = new GameThread(this);
        gameLoop.setRunning(true);
        gameLoop.start();



    }

    public void onWin(){
        finish();
        Intent gameplay = new Intent(this, winActivity.class);
        startActivity(gameplay);
    }

    public void moveZombie(int direction){
        float potentialX=0;
        float potentialY=0;
        switch (direction){
            case 0:
                potentialX=zombie.getX()+size;
                potentialY=zombie.getY();
                break;
            case 1:
                potentialX=zombie.getX()-size;
                potentialY=zombie.getY();
                break;
            case 2:
                potentialY=zombie.getY()-size;
                potentialX=zombie.getX();
                break;
            case 3:
                potentialY=zombie.getY()-size;
                potentialX=zombie.getX();
                break;
        }

        if(potentialX <0){
            potentialX=0;
        }
        if(potentialX>size*15){
            potentialX=size*15;
        }
        if(potentialY<0){
            potentialY=0;
        }
        if(potentialY>8*size){
            potentialY=8*size;
        }
        //collision avec les murs
        for (Wall wall:wallList) {

            if(!(wall.getType()=='W' && (potentialX==wall.getX() || potentialX==wall.getX()+size/2 || potentialX+size/2==wall.getX() || potentialX+size/2==wall.getX()+size/2)&&( potentialY==wall.getY() || potentialY==wall.getY()+size/2 || potentialY+size/2==wall.getY() || potentialY+size/2==wall.getY()+size/2))){

                zombie.setX(potentialX);
                zombie.setY(potentialY);
            }
        }

    }




}
                                                                                        