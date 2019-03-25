package com.iutclermont.labyrintheescape;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SelectLevel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);
    }

    public void onOne(View view){
        Intent menu = new Intent(this,Menu.class);
        menu.putExtra("niv","1");
        startActivity(menu);
    }

    public void onTwo(View view){
        Intent menu = new Intent(this,Menu.class);
        menu.putExtra("niv","2");
        startActivity(menu);
    }

    public void onTree(View view){
        Intent menu = new Intent(this,Menu.class);
        menu.putExtra("niv","3");
        startActivity(menu);
    }

    public void onFour(View view){
        Intent menu = new Intent(this,Menu.class);
        menu.putExtra("niv","4");
        startActivity(menu);
    }
}