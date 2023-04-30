package main.java.inputs;

import main.java.core.Window;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener { //alles selber
    Window w;

    public MouseListener(Window w) {
        this.w = w;
        w.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (w.mM.active) {
            if (w.mM.startButton.contains(MouseInfo.getPointerInfo().getLocation())) {
                w.mM.active = false;
            }
        } else if (!w.level.hud.restarted && w.level.player.health == 0) {
            if (w.level.hud.Exit.contains(MouseInfo.getPointerInfo().getLocation())) {
                w.level.hud.restarted = true;
                System.exit(0);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (w.mM.active) {
            if (w.mM.startButton.contains(MouseInfo.getPointerInfo().getLocation())) {
                w.mM.active = false;
            }
        } else if (!w.level.hud.restarted && w.level.player.health == 0) {
            if (w.level.hud.Exit.contains(MouseInfo.getPointerInfo().getLocation())) {
                w.level.hud.restarted = true;
                System.exit(0);
            }
        } else if (!w.level.hud.restarted && w.level.hud.Coins == 10) {
            if (w.level.hud.Exit.contains(MouseInfo.getPointerInfo().getLocation())) {
                w.level.hud.restarted = true;
                System.exit(0);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
