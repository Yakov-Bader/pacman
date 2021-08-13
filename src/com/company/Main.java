package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame ("Push Counter");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(new MainPanel());
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(48*20,28*20+200));
        frame.pack();
    }
}