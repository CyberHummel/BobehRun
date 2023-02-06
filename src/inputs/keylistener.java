package inputs;

import core.Window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keylistener implements KeyListener {

    private Window w;


    public keylistener(Window w ){
        this.w=w;
        w.addKeyListener(this);
    }

    public void keyTyped(KeyEvent e) {

    }



    private boolean MovingLeft = false;
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_D){
            MovingLeft = false;
            w.player.velx = w.player.speed;
        }

        else if(key == KeyEvent.VK_A){
            MovingLeft = true;
            w.player.velx = -w.player.speed;
        }

        else if(key == KeyEvent.VK_SPACE){
            if(w.player.vely == 0.1){
                w.player.vely = -w.player.JumpVelocity;
            }



        }

    }


    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_D && !MovingLeft){
            w.player.velx = 0;
        }

        if(key == KeyEvent.VK_A && MovingLeft){
            w.player.velx = 0;
        }


    }
}
