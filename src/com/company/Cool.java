package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Cool extends JPanel {
    private final JLabel l1,l2;
    private final JButton button,button1;

    public Cool(){

        button=new JButton("Do you want to hear a joke?");
        button1=new JButton("Do you want to hear another one?");

        l1=new JLabel(" ");
        l2=new JLabel(" ");

        ButtonListener listener = new ButtonListener();

        button.addActionListener (listener);
        button1.addActionListener (listener);

        add(button);
        add(l1);
        add(button1);
        add(l2);

        setPreferredSize (new Dimension(500, 500));
        setBackground (Color.GREEN);
    }
    private class ButtonListener implements ActionListener {

        public void actionPerformed (ActionEvent event) {
            if(Objects.equals(event.getActionCommand(), "Do you want to hear a joke?")){
                l1.setText("why couldn't the puny sing a song?"+"   Because she was a little horse :)");
            }else{
                l2.setText("Why did the kid bring a ladder to school?" +"   Because she wanted to go to high school:)");
            }
        }
    }

}
