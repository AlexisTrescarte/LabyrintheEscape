package com.iutclermont.labyrintheescape;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
    }

    public void onMenu(View view){
        finish();
    }

    public void onRetry(View view){

    }
}
