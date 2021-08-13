package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame ("Pacman");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MainPanel());
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(48*21,28*20+100));
        frame.pack();
    }
}