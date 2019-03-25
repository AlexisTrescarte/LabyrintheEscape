package com.iutclermont.labyrintheescape;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

import java.io.IOException;
import java.util.List;

import static java.lang.Math.abs;

public class GameActivity extends AppCompatActivity {

    private Personage personnage;
    private Personage zombie;
    private SensorManager mSensorManager = null;
    private Sensor mAccelerometer = null;
    private List<Wall> wallList;
    private FileReader reader;
    private GameThread gameLoop;
    private int size;
    private int width;
    private String niveau;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //avoir la taille de l'écran
        Display ecran = getWindowManager().getDefaultDisplay();
        width= ecran.getWidth();


        this.niveau =getIntent().getStringExtra("level");


        setPersonnage(new Personage(0,0));
        setZombie(new Personage(width-width/16,8*width/9));


        //On récupére les item du niveau
        reader=new FileReader(niveau);
        try {
            setWallList(reader.getWall(width,this));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //canvas = new GameView(this);
        setContentView(R.layout.activity_gameplay);


        // Boucle de jeux
        gameLoop = new GameThread(this);
        gameLoop.setRunning(true);
        gameLoop.start();


        //taille des objet
        size = width/16;

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

                if((abs(x)>2 || abs(y)>2) && getPersonnage().getCanMove() ){

                    getPersonnage().setCanMove(false);
                    if(abs(x)>abs(y)){
                        if(x>0){
                            potentialX= getPersonnage().getX()+size/2;
                        }
                        else{
                            potentialX= getPersonnage().getX()-size/2;
                        }
                         potentialY= getPersonnage().getY();
                    }
                    else{
                        potentialX= getPersonnage().getX();
                        if(y>0){
                            potentialY= getPersonnage().getY()+size/2;
                        }
                        else{
                            potentialY= getPersonnage().getY()-size/2;
                        }
                    }

                    // Puis on modifie les coordonnées en fonction de la vitesse
                    getPersonnage().setVisible(true);
                    collision=false;

                    //vérification de la position dans l'espace de jeux
                    if(potentialX <0){
                        potentialX=0;
                    }
                    if(potentialX>width-size){
                        potentialX=width-size;
                    }
                    if(potentialY<0){
                        potentialY=0;
                    }
                    if(potentialY>9*size-size){
                        potentialY=9*size-size;
                    }
                    //collision avec les murs
                    for (Wall wall: getWallList()) {

                        if((potentialX==wall.getX() || potentialX==wall.getX()+size/2 || potentialX+size/2==wall.getX() || potentialX+size/2==wall.getX()+size/2)&&( potentialY==wall.getY() || potentialY==wall.getY()+size/2 || potentialY+size/2==wall.getY() || potentialY+size/2==wall.getY()+size/2)){
                            switch (wall.getType()){
                                case 'W' :
                                    collision=true;
                                    break;
                                case 'B':
                                    getPersonnage().setVisible(false);
                                    break;
                                case 'A':
                                    //stauper le mouvement !
                                    mSensorManager.unregisterListener(this);
                                    onWin();
                                    break;
                            }
                        }
                    }
                    if(personnage.getVisible()==true && (personnage.getX()==zombie.getX() || personnage.getX()==zombie.getX()+size/2 || personnage.getX()+size/2==zombie.getX() || personnage.getX()+size/2==zombie.getX()+size/2) && (personnage.getY()==zombie.getY() || personnage.getY()==zombie.getY()+size/2 || personnage.getY()+size/2==zombie.getY() || personnage.getY()+size/2==zombie.getY()+size/2)){
                        onLoose();
                    }
                }

                if(collision==false){
                    getPersonnage().setX(potentialX);
                    getPersonnage().setY(potentialY);
                }

                getPersonnage().setCanMove(true);


            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }

        };
        mSensorManager.registerListener(mSensorEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);


    }

    public void onWin(){
        gameLoop.setRunning(false);
        finish();
        Intent win = new Intent(this, WinActivity.class);
        startActivity(win);
    }

    public void onLoose(){
        gameLoop.setRunning(false);
        finish();

    }

    public void moveZombie(int direction){
        Boolean collision=false;
        float potentialX=0;
        float potentialY=0;
        switch (direction){
            case 0:
                potentialX= getZombie().getX()+size/2;
                potentialY= getZombie().getY();
                break;
            case 1:
                potentialX= getZombie().getX()-size/2;
                potentialY= getZombie().getY();
                break;
            case 2:
                potentialY= getZombie().getY()-size/2;
                potentialX= getZombie().getX();
                break;
            case 3:
                potentialY= getZombie().getY()-size/2;
                potentialX= getZombie().getX();
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
        for (Wall wall: getWallList()) {

            if((wall.getType()=='W') && (potentialX==wall.getX() || potentialX==wall.getX()+size/2 || potentialX+size/2==wall.getX() || potentialX+size/2==wall.getX()+size/2) && ( potentialY==wall.getY() || potentialY==wall.getY()+size/2 || potentialY+size/2==wall.getY() || potentialY+size/2==wall.getY()+size/2)){
                collision=true;
            }
        }

        if(collision==false){
            getZombie().setX(potentialX);
            getZombie().setY(potentialY);
        }

    }


    public Personage getPersonnage() {
        return personnage;
    }

    public void setPersonnage(Personage personnage) {
        this.personnage = personnage;
    }

    public Personage getZombie() {
        return zombie;
    }

    public void setZombie(Personage zombie) {
        this.zombie = zombie;
    }

    public List<Wall> getWallList() {
        return wallList;
    }

    public void setWallList(List<Wall> wallList) {
        this.wallList = wallList;
    }

    public int getWidth() {
        return width;
    }
}
                                                                                        