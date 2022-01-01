package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {
    private final JButton game,cool;
    private final JLabel title;
    private JPanel page,Cool,GamePage;


    public MainPanel() {

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

        page.setPreferredSize (new Dimension(48*25, 28*20+200));
        page.setBackground (Color.cyan);

        add(page);
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed (ActionEvent event) {
            page.setVisible(false);
            page.setEnabled(false);
            page.setOpaque(false);
            if(event.getActionCommand().equals("see something cool")){
                Cool=new Cool();
                add(Cool);
                Cool.requestFocus();
            }else{
                GamePage=new GamePage(1);
                add(GamePage);
                GamePage.requestFocus();
            }
        }
    }

}


