package main.java.inputs;

import main.java.core.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keylistener implements KeyListener {
    private final Window w;
    public boolean Moving = false;


    public keylistener(Window w ){
        this.w=w;
        w.addKeyListener(this);
    }

    public void keyTyped(KeyEvent e) {

    }



    public boolean MovingLeft = false;
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_D){
            Moving = true;
            w.level.player.velx = w.level.player.speed;
            MovingLeft = false;


        }

        else if(key == KeyEvent.VK_A){
            Moving = true;
            MovingLeft = true;
            w.level.player.velx = -w.level.player.speed;


        }

        else if(key == KeyEvent.VK_SPACE){
            if(w.level.player.jumpable) {
                w.level.player.y -= 4;
                w.level.player.hitBox.y -= 4;
                w.level.player.y -= 4;
                w.level.player.hitBox.y -= 4;
                w.level.player.vely = -w.level.player.JumpVelocity;
                w.level.player.jumping = true;
            }
        }

    }


    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_D && !MovingLeft){
            Moving = false;
            w.level.player.velx = 0;

        }

        if(key == KeyEvent.VK_A && MovingLeft){
            Moving = false;
            w.level.player.velx = 0;

        }

        if(key == KeyEvent.VK_SPACE){
            w.level.player.jumping = false;
            w.level.player.falling = true;
        }


    }
}
