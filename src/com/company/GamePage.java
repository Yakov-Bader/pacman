package com.company;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class GamePage extends JPanel {
    long startTime, endTime;
    float duration;
    private final Timer timer,timer1,timer2,timer3;
    int SQRSIZE, MSIZE, BLUE, YELLOW, DOTTED, HERE,FRONT,BACK,NONE;
    JPanel game;
    Player p;
    Ghosts g1,g2,g3,g4;
    Matrix mat;
    int[][] board;

    public GamePage(int level){

        mat=new Matrix();
        if(level==1){
            board=mat.arr1.clone();
        }else if(level==2){
            board=mat.arr2.clone();
        }
        game=new JPanel();
        game.add(new MyGraphics());

        p=new Player(60,300);
        g1=new Ghosts(20,20);
        g2=new Ghosts(920,520);
        g3=new Ghosts(920,20);
        g4=new Ghosts(20,520);

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
        ActionListener action3 = evt -> {
            updateG(g4.getDirection(),g4);
            win();
            repaint();
        };
        timer = new Timer( 30, action);
        timer1 = new Timer( 30, action1);
        timer2 = new Timer( 30, action2);
        timer3 = new Timer( 30, action3);

        addKeyListener(new DirectionListener());


        addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                timer.start();
                timer1.start();
                timer2.start();
                timer3.start();
                repaint();
            }
            public void focusLost(FocusEvent e) {
                timer.stop();
                timer1.stop();
                timer2.stop();
                repaint();
            }
        });

        add(game);
        setPreferredSize (new Dimension(SQRSIZE*48, SQRSIZE*28+50));
        setFocusable(true);
        startTime = System.currentTimeMillis();
    }
    public class MyGraphics extends JComponent {

        MyGraphics() {
            setPreferredSize (new Dimension(960, 650));
        }

        @Override
        public void paint(Graphics g) {

            super.paintComponent (g);
            for (int i=0;i<board.length;i++){
                for (int j=0;j<board[i].length;j++){
                    if(board[i][j]==BLUE){
                        g.setColor(Color.BLUE);
                        g.fillRect (j*SQRSIZE, i*SQRSIZE, SQRSIZE, SQRSIZE);
                    }else if(board[i][j]==DOTTED){
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

            g.setColor(Color.PINK);
            g.fillRect(g4.getX(),g4.getY(),MSIZE,MSIZE);

            g.setColor(Color.red);
            g.setFont(new Font("TimesRoman", Font.PLAIN, SQRSIZE));
            g.drawString("you have "+p.getPoints()+" points", 400, SQRSIZE*29);
            g.drawString(df.format(duration)+ " minutes left", 400, SQRSIZE*30);

        }
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
                if(board[p.getY()/SQRSIZE][(p.getX()+MSIZE)/SQRSIZE]!=BLUE){
                    board[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]=YELLOW;
                    p.setX(p.getX()+FRONT*5);
                    if(board[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]==DOTTED){
                        p.setPoints();
                    }
                    board[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]=HERE;
                }
                break;
            case -1:
                if(board[p.getY()/SQRSIZE][(p.getX()+BACK)/SQRSIZE]!=BLUE){
                    board[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]=YELLOW;
                    p.setX(p.getX()+BACK*5);
                    if(board[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]==DOTTED){
                        p.setPoints();
                    }
                    board[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]=HERE;
                }
                break;
        }
        switch (vertical) {
            case 1:
                if(board[(p.getY()+MSIZE)/SQRSIZE][p.getX()/SQRSIZE]!=BLUE){
                    board[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]=YELLOW;
                    p.setY(p.getY()+FRONT*5);
                    if(board[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]==DOTTED){
                        p.setPoints();
                    }

                    board[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]=HERE;
                }
                break;
            case -1:
                if(board[(p.getY()+BACK)/SQRSIZE][p.getX()/SQRSIZE]!=BLUE){
                    board[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]=YELLOW;
                    p.setY(p.getY()+BACK*5);
                    if(board[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]==DOTTED){
                        p.setPoints();
                    }
                    board[p.getY()/SQRSIZE][(p.getX())/SQRSIZE]=HERE;
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
                if(board[(ghost.getY())/SQRSIZE][(ghost.getX()+MSIZE)/SQRSIZE]==BLUE){
                    ghost.setDirection(ghost.getRand());
                }else{
                    ghost.setX(ghost.getX()+FRONT);
                }
                if(board[(ghost.getY())/SQRSIZE][ghost.getX()/SQRSIZE]==HERE||duration<=0){
                    lose();
                }
                break;
            case 1:
                if(board[(ghost.getY())/SQRSIZE][(ghost.getX()+BACK)/SQRSIZE]==BLUE){
                    ghost.setDirection(ghost.getRand());
                }else{
                    ghost.setX(ghost.getX()+BACK);
                }
                if(board[(ghost.getY())/SQRSIZE][ghost.getX()/SQRSIZE]==HERE||duration<=0){
                    lose();
                }
                break;
            case 2:
                if(board[(ghost.getY()+MSIZE)/SQRSIZE][ghost.getX()/SQRSIZE]==BLUE){
                    ghost.setDirection(ghost.getRand());
                }else{
                    ghost.setY(ghost.getY()+FRONT);
                }
                if(board[(ghost.getY())/SQRSIZE][ghost.getX()/SQRSIZE]==HERE||duration<=0){
                    lose();
                }
                break;
            case 3:
                if(board[(ghost.getY()+BACK)/SQRSIZE][ghost.getX()/SQRSIZE]==BLUE){
                    ghost.setDirection(ghost.getRand());
                }else{
                    ghost.setY(ghost.getY()+BACK);
                }
                if(board[(ghost.getY())/SQRSIZE][ghost.getX()/SQRSIZE]==HERE||duration<=0){
                    lose();
                }
                break;

        }

    }
    public void lose() {
        game.setVisible(false);
        game.setEnabled(false);
        game.setOpaque(false);
        Lose lose=new Lose(false);
        add(lose);
    }
    public void win(){
        int win=0;
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[i].length;j++){
                if(board[i][j]==DOTTED){
                   win++;
                }
            }
        }
        if(win==0){
            game.setVisible(false);
            game.setEnabled(false);
            game.setOpaque(false);
            Lose won=new Lose(true);
            add(won);
        }

    }
}
