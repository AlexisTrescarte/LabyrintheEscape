package com.iutclermont.labyrintheescape;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ScoresActivity extends AppCompatActivity {
    private ListView scoreListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        scoreListView = (ListView) findViewById(R.id.listView);
        Joueur clement = new Joueur("Clement", 5);
        Joueur alexis = new Joueur("Alexis", 6);
        Joueur[] players = new Joueur[]{clement,alexis};
        ArrayAdapter<Joueur> arrayAdapter = new ArrayAdapter<Joueur>(this,android.R.layout.simple_list_item_1,players);
        scoreListView.setAdapter(arrayAdapter);
    }
}
