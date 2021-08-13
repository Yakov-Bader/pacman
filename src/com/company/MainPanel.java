package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {
    private final JButton game,cool;
    private final JLabel title;
    private final JPanel page,Cool,GamePage;


    public MainPanel() {

        Cool=new Cool();
        GamePage=new GamePage();
        page= new JPanel();

        ButtonListener listener = new ButtonListener();

        game=new JButton("start game");
        game.addActionListener (listener);

        cool=new JButton("see something cool");
        cool.addActionListener (listener);

        title= new JLabel("WELCOME TO POCMAN");

        page.add(title);
        page.add(game);
        page.add(cool);

        page.setPreferredSize (new Dimension(48*20, 28*20+100));
        page.setBackground (Color.cyan);

        add(page);
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed (ActionEvent event) {
            page.setVisible(false);
            if(event.getActionCommand().equals("see something cool")){
                add(Cool);
            }else{
                add(GamePage);
                GamePage.requestFocus();
            }
        }
    }


}


