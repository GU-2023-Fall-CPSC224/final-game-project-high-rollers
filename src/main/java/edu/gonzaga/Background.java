package edu.gonzaga;

import javax.swing.*;

public class Background {
    public void background(){
        JFrame frame = new JFrame("Background Image Example");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JLabel with the image
        ImageIcon backgroundImage = new ImageIcon("PNG-cards-1.3/intro.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);

        // Set the layout manager of the layered pane to null
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);

        // Set the bounds of the background label to cover the entire frame
        backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        // Add the background label to the layered pane at the DEFAULT_LAYER
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

        // Set the content pane of the frame to the layered pane
        frame.setContentPane(layeredPane);

        // Now, you can add other components to the frame as needed

        frame.setVisible(true);
    }
}
