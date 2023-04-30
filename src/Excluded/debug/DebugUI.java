package core.debug;

import core.Window;

import javax.swing.*;
import java.awt.*;

public class DebugUI {

    Window w;
    int x, y;
    JLabel FPS = new JLabel();


    public DebugUI(Window w, int x, int y){
        this.w=w;
        this.x=x;
        this.y=y;

    }

    public void Render(Graphics g){
    FPS.setVisible(true);
    FPS.setLocation(x-10, y+5);
    FPS.setText("Test");
    }

    public void tick(){
    FPS.setText("LOL");
    }
}