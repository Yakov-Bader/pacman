package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Cool extends JPanel {
    private final JLabel l1,l2,l3;
    private final JButton button,button1,button2;
    private JPanel GamePage, cool;

    public Cool(){

        cool=new JPanel();

        button=new JButton("Do you want to hear a joke?");
        button1=new JButton("Do you want to hear another one?");
        button2=new JButton("play");

        l1=new JLabel(" ");
        l2=new JLabel(" ");
        l3=new JLabel(" ");

        ButtonListener listener = new ButtonListener();

        button.addActionListener (listener);
        button1.addActionListener (listener);
        button2.addActionListener (listener);

        cool.add(button);
        cool.add(l1);
        cool.add(button1);
        cool.add(l2);
        cool.add(button2);
        cool.add(l3);

        cool.setPreferredSize (new Dimension(500, 500));
        cool.setBackground (Color.GREEN);

        add(cool);

    }
    private class ButtonListener implements ActionListener {

        public void actionPerformed (ActionEvent event) {
            if(event.getActionCommand().equals("Do you want to hear a joke?")){
                l1.setText("why couldn't the puny sing a song?"+"   Because she was a little horse :)");
            }else if(event.getActionCommand().equals("Do you want to hear another one?")){
                l2.setText("Why did the kid bring a ladder to school?" +"   Because she wanted to go to high school:)");
            }else {

                cool.setVisible(false);
                cool.setEnabled(false);
                cool.setOpaque(false);

                GamePage=new GamePage(1);
                add(GamePage);
                GamePage.requestFocus();
            }
        }
    }

}
