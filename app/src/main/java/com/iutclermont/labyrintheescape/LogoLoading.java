package com.iutclermont.labyrintheescape;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import static android.os.SystemClock.sleep;

public class LogoLoading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logoloading);



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
        return true;
    }


}
