package com.company;

public class Player{
     int x;
     int y;
     int points;
    public Player(int x,int y){
        this.x=x;
        this.y=y;
        points=0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints() {
        points++;
    }
}
