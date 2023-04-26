package main.java.inputs;

import main.java.core.Window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keylistener extends Thread implements KeyListener {       //gesamte Klasse selber
    private final Window w;
    public boolean Moving = false;
    volatile int key;

    public keylistener(Window w) {
        this.w = w;
        w.addKeyListener(this);
    }

    public void keyTyped(KeyEvent e) {
        key = e.getKeyCode();
    }

    public boolean MovingLeft = false;

    public void keyPressed(KeyEvent e) {
        Moving = true;
        //System.out.println(Moving);
        //System.out.println(MovingLeft);
        int key = e.getKeyCode();
        this.key = key;

        if (key == KeyEvent.VK_D) {
            Moving = true;
            w.level.player.velx = w.level.player.speed;
            MovingLeft = false;
            w.level.player.directionX = 1;
        } else if (key == KeyEvent.VK_A) {
            Moving = true;
            MovingLeft = true;
            w.level.player.velx = -w.level.player.speed;
            w.level.player.directionX = -1;
        } else if (key == KeyEvent.VK_SPACE) {
            if (w.level.player.jumpable) {
                w.level.player.y -= 4;
                w.level.player.hitBoxFeet.y -= 4;
                w.level.player.y -= 4;
                w.level.player.hitBoxFeet.y -= 4;
                w.level.player.vely = -w.level.player.JumpVelocity;
                w.level.player.jumping = true;
            }
        } else if (key == KeyEvent.VK_Q) {
            key = KeyEvent.VK_Q;
        }
    }


    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_D && !MovingLeft) {
            Moving = false;
            w.level.player.velx = 0;
            w.level.player.directionX = 0;
        }

        if (key == KeyEvent.VK_A && MovingLeft) {
            Moving = false;
            w.level.player.velx = 0;
            w.level.player.directionX = 0;
        }

        if (key == KeyEvent.VK_SPACE) {
            w.level.player.jumping = false;
            w.level.player.falling = true;
        }

    }

    public void run(){
        while (w.level.player.alive){// speichere den aktuellen Wert von key
            if(key == KeyEvent.VK_Q){ // überprüfe den gespeicherten Wert von key
                System.out.println("s");
                w.level.player.Attack_Q();
                try {
                    Thread.sleep(w.level.player.attackDelay_Q);
                    System.out.println("You can attack!");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
