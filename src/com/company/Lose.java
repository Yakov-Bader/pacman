package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class Lose extends JPanel {
    JLabel massage;
    JButton replay,finish;
    JPanel bye;
    public Lose(boolean win){

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
                massage.setText("you thought it will be so easy to get another free game?");
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
