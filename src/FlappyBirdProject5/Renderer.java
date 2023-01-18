package FlappyBirdProject5;

import FlappyBirdProject5.FlappyBird;

import javax.swing.*;
import java.awt.*;

public class Renderer extends JPanel {
    private static final long serialVersionUID = 1L;

    //overrides the paintComponent method from JPanel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        FlappyBird.flappyBird.repaint(g);

    }
}
