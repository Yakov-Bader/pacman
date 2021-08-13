package com.company;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class GamePage extends JPanel {
    long startTime, endTime;
    float duration;
    private final Timer timer,timer1,timer2;
    int SQRSIZE, MSIZE, BLUE, YELLOW, DOTTED, HERE,FRONT,BACK,NONE;
    Player p;
    Ghosts g1,g2,g3;
    Matrix mat;
    CardLayout card;

    public GamePage(){
        p=new Player(20,20);
        g1=new Ghosts(20,20);
        g2=new Ghosts(920,520);
        g3=new Ghosts(920,20);
        mat=new Matrix();

        SQRSIZE=20;
        MSIZE=10;
        BLUE=1;
        YELLOW=2;
        DOTTED=0;
        HERE=5;
        FRONT=1;
        BACK=-1;
        NONE=0;

        ActionListener action = evt -> {
            updateG(g1.getDirection(),g1);
            win();
            repaint();
        };
        ActionListener action1 = evt -> {
            updateG(g2.getDirection(),g2);
            win();
            repaint();
        };
        ActionListener action2 = evt -> {
            updateG(g3.getDirection(),g3);
            win();
            repaint();
        };
        timer = new Timer( 30, action);
        timer1 = new Timer( 30, action1);
        timer2 = new Timer( 30, action2);

        addKeyListener(new DirectionListener());


        addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                timer.start();
                timer1.start();
                timer2.start();
                repaint();
            }
            public void focusLost(FocusEvent e) {
                timer.stop();
                timer1.stop();
                timer2.stop();
                repaint();
            }
        });


        setBackground (Color.PINK);
        setPreferredSize (new Dimension(SQRSIZE*48, SQRSIZE*48+200));
        startTime = System.currentTimeMillis();
        setFocusable(true);
    }
    public void paint(Graphics g) {

        super.paintComponent (g);
        for (int i=0;i<mat.arr1.length;i++){
            for (int j=0;j<mat.arr1[i].length;j++){
                if(mat.arr1[i][j]==BLUE){
                    g.setColor(Color.BLUE);
                    g.fillRect (j*SQRSIZE, i*SQRSIZE, SQRSIZE, SQRSIZE);
                }else if(mat.arr1[i][j]==DOTTED){
                    g.setColor(Color.orange);
                    g.fillRect (j*SQRSIZE, i*SQRSIZE, SQRSIZE, SQRSIZE);

                    g.setColor(Color.red);
                    g.fillOval (j*SQRSIZE+5, i*SQRSIZE+5, SQRSIZE/2, SQRSIZE/2);
                }else{
                    g.setColor(Color.orange);
                    g.fillRect (j*SQRSIZE, i*SQRSIZE, SQRSIZE, SQRSIZE);
                }
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");
        g.setColor(Color.BLACK);
        g.fillOval(p.getX(),p.getY(),MSIZE,MSIZE);

        g.setColor(Color.green);
        g.fillRect(g1.getX(),g1.getY(),MSIZE,MSIZE);

        g.setColor(Color.white);
        g.fillRect(g2.getX(),g2.getY(),MSIZE,MSIZE);

        g.setColor(Color.cyan);
        g.fillRect(g3.getX(),g3.getY(),MSIZE,MSIZE);
        g.setColor(Color.red);

        g.setFont(new Font("TimesRoman", Font.PLAIN, SQRSIZE));
        g.drawString("you have "+p.getPoints()+" points", 400, SQRSIZE*28+50);
        g.drawString(df.format(duration)+ "minutes left", 400, SQRSIZE*28+80);

    }



    private class DirectionListener extends KeyAdapter  {

        @Override
        public void keyPressed(KeyEvent event) {

            switch (event.getKeyCode()) {
                case KeyEvent.VK_UP:
                    upadateP(NONE,BACK);
                    repaint();
                    break;
                case KeyEvent.VK_DOWN:
                    upadateP(NONE,FRONT);
                    repaint();
                    break;
                case KeyEvent.VK_LEFT:
                    upadateP(BACK,NONE);
                    repaint();
                    break;
                case KeyEvent.VK_RIGHT:
                    upadateP(FRONT,NONE);
                    repaint();
                    break;
            }
            repaint();
        }

    }
    public void upadateP(int horizontal,int vertical) {
        switch (horizontal) {
            case 1:
                if(mat.arr1[p.getY()/SQRSIZE][(p.getX()+MSIZE)/SQRSIZE]!=BLUE){
                    mat.arr1[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]=YELLOW;
                    p.setX(p.getX()+FRONT);
                    if(mat.arr1[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]==DOTTED){
                        p.setPoints();
                    }
                    mat.arr1[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]=HERE;
                }
                break;
            case -1:
                if(mat.arr1[p.getY()/SQRSIZE][(p.getX()+BACK)/SQRSIZE]!=BLUE){
                    mat.arr1[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]=YELLOW;
                    p.setX(p.getX()+BACK);
                    if(mat.arr1[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]==DOTTED){
                        p.setPoints();
                    }
                    mat.arr1[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]=HERE;
                }
                break;
        }
        switch (vertical) {
            case 1:
                if(mat.arr1[(p.getY()+MSIZE)/SQRSIZE][p.getX()/SQRSIZE]!=BLUE){
                    mat.arr1[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]=YELLOW;
                    p.setY(p.getY()+FRONT);
                    if(mat.arr1[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]==DOTTED){
                        p.setPoints();
                    }

                    mat.arr1[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]=HERE;
                }
                break;
            case -1:
                if(mat.arr1[(p.getY()+BACK)/SQRSIZE][p.getX()/SQRSIZE]!=BLUE){
                    mat.arr1[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]=YELLOW;
                    p.setY(p.getY()+BACK);
                    if(mat.arr1[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]==DOTTED){
                        p.setPoints();
                    }
                    mat.arr1[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]=HERE;
                }
                break;
        }

    }

    public void updateG(int dir, Ghosts ghost){
        endTime = System.currentTimeMillis();
        duration=(endTime-startTime)/1000F;
        duration=duration/60F;
        duration=5-duration;
        switch (dir){
            case 0:
                if(mat.arr1[(ghost.getY())/SQRSIZE][(ghost.getX()+MSIZE)/SQRSIZE]==BLUE){
                    ghost.setDirection(ghost.getRand());
                }else{
                    ghost.setX(ghost.getX()+FRONT);
                }
                if(mat.arr1[(ghost.getY())/SQRSIZE][ghost.getX()/SQRSIZE]==HERE||duration<=0){
                    lose();
                }
                break;
            case 1:
                if(mat.arr1[(ghost.getY())/SQRSIZE][(ghost.getX()+BACK)/SQRSIZE]==BLUE){
                    ghost.setDirection(ghost.getRand());
                }else{
                    ghost.setX(ghost.getX()+BACK);
                }
                if(mat.arr1[(ghost.getY())/SQRSIZE][ghost.getX()/SQRSIZE]==HERE||duration<=0){
                    lose();
                }
                break;
            case 2:
                if(mat.arr1[(ghost.getY()+MSIZE)/SQRSIZE][ghost.getX()/SQRSIZE]==BLUE){
                    ghost.setDirection(ghost.getRand());
                }else{
                    ghost.setY(ghost.getY()+FRONT);
                }
                if(mat.arr1[(ghost.getY())/SQRSIZE][ghost.getX()/SQRSIZE]==HERE||duration<=0){
                    lose();
                }
                break;
            case 3:
                if(mat.arr1[(ghost.getY()+BACK)/SQRSIZE][ghost.getX()/SQRSIZE]==BLUE){
                    ghost.setDirection(ghost.getRand());
                }else{
                    ghost.setY(ghost.getY()+BACK);
                }
                if(mat.arr1[(ghost.getY())/SQRSIZE][ghost.getX()/SQRSIZE]==HERE||duration<=0){
                    lose();
                }
                break;

        }

    }
    public void lose() {
        card.next(this);
    }
    public void win(){
        int win=0;
        for (int i=0;i<mat.arr1.length;i++){
            for (int j=0;j<mat.arr1[i].length;j++){
                if(mat.arr1[i][j]==DOTTED){
                   win++;
                }
            }
        }
        if(win==0){
            Lose won=new Lose(true);
            Lose lose=new Lose(false);
            add(won,"you win");
            add(lose,"you lose");

        }

    }
}
