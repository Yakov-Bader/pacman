package com.company;
import java.util.Random;

public class Ghosts extends Player {
    int direction;

    public Ghosts(int x, int y) {
        super(x,y);
        direction=getRand();
    }
    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getRand(){Random rand = new Random();
        return rand.nextInt(4);
    }

}
