package com.oxilapp.somegame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends  Thread {

    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    //private SurfaceHolder surfaceHolder;
    //private GameView gameView;
    private boolean running;
    public static Canvas canvas;
    //private  int targetFPS = 30;
    //private double averageFPS;


    /**
     * @param surfaceHolder
     * @param gameView
     */
    public MainThread(SurfaceHolder surfaceHolder, GameView gameView) {

        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;

    }

    @Override
    public void run() {
        while (running) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized(surfaceHolder) {
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            } catch (Exception e) {} finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public boolean setRunning(boolean isRunning) {
        running = isRunning;
        return running;
    }
}