package com.iutclermont.labyrintheescape;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class FileReader {

    private int chemin;

    public FileReader(String niv){
        int niveau = parseInt(niv);
        switch (niveau){
            case 1 : chemin = R.raw.niv1;break;
            case 2 : chemin = R.raw.niv2;break;
            case 3 : chemin = R.raw.niv3;break;
            case 4 : chemin = R.raw.niv4;break;
        }
    }

    public List<Wall> getWall(int width, Context context) throws IOException {

        List<Wall> result = new LinkedList<>();
        int size=width/16;
        int content;
        int x=0;
        int y=0;
        int i = 0;


        InputStream myFich =context.getResources().openRawResource(chemin);


        while ((content = myFich.read()) != -1) {
            switch ((char)content){
                        case '1' :
                            Wall wall = new Wall(x,y,'W');
                            result.add(wall);break;
                        case 'A' :
                            Wall arrive = new Wall(x,y,'A');
                            result.add(arrive);break;
                        case 'B':
                            Wall bush = new Wall(x,y,'B');
                            result.add(bush);break;
            }
            if(i>16){
                i=0;
                x=0;
                y=y+size;
            }
            else{
                i++;
                x=x+size;
            }
        }
        return result;
    }
}
