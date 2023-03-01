package core;
import javax.swing.*;
import java.awt.*;

public class BesseresWindow extends JPanel {
    public BesseresWindow(){

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        repaint();
    }


}
