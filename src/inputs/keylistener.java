package inputs;

import core.Window;
import level.LevelHandler;

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
            w.level.cameraX += w.level.player.velx+w.player.speed;
            MovingLeft = false;
            w.level.player.velx = w.level.player.speed;
        }

        else if(key == KeyEvent.VK_A){
            w.level.cameraX += w.level.player.velx-w.level.player.speed;
            MovingLeft = true;
            w.level.player.velx = -w.level.player.speed;
        }

        else if(key == KeyEvent.VK_SPACE){

            if(w.level.player.vely == 0.1){
                w.level.player.vely = -w.level.player.JumpVelocity;
            }



        }

    }


    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_D && !MovingLeft){
            w.level.player.velx = 0;
        }

        if(key == KeyEvent.VK_A && MovingLeft){
            w.level.player.velx = 0;
        }


    }
}
