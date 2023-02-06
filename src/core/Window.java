package core;

import objects.Player;
import inputs.keylistener;
import level.LevelHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class Window extends Canvas implements Runnable{

    private static final long serialVersionUID = 1L;
    private Thread thread;
    private boolean running = false;
    private final keylistener Keylistener = new keylistener(this);

    public LevelHandler level = new LevelHandler(this);


    //GameObjects
    public Player player = new Player(this,100, 100, 42, 42);
    //GameObjects



    public Window( String Title, int Heigth, int Width){
        JFrame frame = new JFrame(Title);

        frame.setSize(Heigth, Width);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(this);
        frame.setBackground(Color.white);
    }


    public void start(){
        thread = new Thread(this);
        thread.start();
        running = true;


    }

    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void  Render(){
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null){
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0, this.getWidth(), this.getHeight());

        level.Render(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, level.floorHeight +41, this.getWidth(), this.getHeight());

        bs.show();
        g.dispose();
    }
    public void tick(){
        level.tick();
    }

    @Override
    public void run() {
        long lasTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 /amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis(); //is for FPS
        int updates = 0;
        int frames = 0;


        while (running){
            long now = System.nanoTime();
            delta += (now - lasTime) / ns;
            lasTime = now;
            while (delta >= 1){
                tick();
                updates++;
                delta--;
            }
            Render();
            frames++;

            if(System.currentTimeMillis() - timer >1000){
                timer += 1000;
                System.out.println("FPS:" + frames + "Ticks:" + updates);
                frames = 0;
                updates = 0;
            }
        }

        stop();
    }

    //update Window

    //paint window

}
