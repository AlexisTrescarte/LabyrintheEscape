package com.iutclermont.labyrintheescape;

public class Personage {

    private float X;
    private float Y;



    private Boolean visible;
    private Boolean canMove;


    public Personage(int x,int y){
        this.visible=true;
        this.X=x;
        this.Y=y;
    }


    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getCanMove() {
        return canMove;
    }

    public void setCanMove(Boolean canMove) {
        this.canMove = canMove;
    }

    public Boolean getVisible() {
        return visible;
    }
}
