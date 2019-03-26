package com.iutclermont.labyrintheescape;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SelectLevel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);
        finish();
    }

    public void onOne(View view){
        getIntent().putExtra("niveau",1);
    }

    public void onTwo(View view){
        getIntent().putExtra("niveau",2);
    }

    public void onTree(View view){
        getIntent().putExtra("niveau",3);
    }

    public void onFour(View view){
        getIntent().putExtra("niveau",4);
    }
}
