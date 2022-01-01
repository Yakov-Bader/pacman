package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class Lose extends JPanel {
    JLabel massage;
    JButton replay,finish;
    JPanel bye,nextLevel, thisLevel;
    boolean winer;
    public Lose(boolean win){
        winer=win;
        nextLevel=new GamePage(2);
        thisLevel=new GamePage(1);
        ButtonListener listener = new ButtonListener();
        if(win){
            massage=new JLabel("yay, you won!!!!! good job, now you could thank me for the game");
        }else{
            massage=new JLabel("you are such a looser!!!, but you could tray again");
        }

        bye=new JPanel();

        bye.add(massage);

        replay=new JButton("replay");
        replay.addActionListener (listener);
        bye.add(replay);

        finish=new JButton("finish");
        finish.addActionListener (listener);
        bye.add(finish);

        bye.setPreferredSize (new Dimension(500, 500));
        bye.setBackground (Color.cyan);
        add(bye);
    }
    private class ButtonListener implements ActionListener {

        public void actionPerformed (ActionEvent event) {
            if(event.getActionCommand().equals("replay")){
                bye.setVisible(false);
                bye.setEnabled(false);
                bye.setOpaque(false);
                if(winer){
                    add(nextLevel);
                    nextLevel.requestFocus();
                }else{
                    add(thisLevel);
                    thisLevel.requestFocus();
                }
            }else{
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                JComponent comp = (JComponent) event.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
            }
        }
    }

}
