package com.iutclermont.labyrintheescape;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class Menu extends AppCompatActivity {

    private int niveaux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        this.niveaux=1;
    }

    public void onPlay(View view){
        Intent gameplay = new Intent(this, GameManager.class);
        startActivity(gameplay);
    }

    public void onSelectLevel(View view){
        Intent selectLevel = new Intent(this,SelectLevel.class);
        startActivity(selectLevel);

        if(selectLevel.hasExtra("niv")){
            this.niveaux=selectLevel.getIntExtra("niv",1);
            System.out.println(this.niveaux+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        super.onTouchEvent(event);
        return true;
    }

    public void onScores(View view){
        Intent scoresActivity = new Intent(this,ScoresActivity.class);
        startActivity(scoresActivity);
    }

    public void onExit(View view){
        finish();
    }
}
