package com.example.meneth.exercise1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import sheep.game.Camera;
import sheep.game.State;
import sheep.game.World;
import sheep.input.TouchListener;

/**
 * Created by Meneth on 2016-02-07.
 */
public class SpritesGameState extends State {
    private World gameWorld;
    private SpritesGameLayer gameLayer;

    public SpritesGameState() {
        gameWorld = new World();
        gameLayer = new SpritesGameLayer();
        gameWorld.addLayer(gameLayer);
        this.addTouchListener(new TouchListener() {
            @Override
            public boolean onTouchDown(MotionEvent event) {
                return false;
            }

            @Override
            public boolean onTouchUp(MotionEvent event) {
                gameLayer.getHelicopter().setTarget(event.getRawX(), event.getRawY());
                return true;
            }

            @Override
            public boolean onTouchMove(MotionEvent event) {
                gameLayer.getHelicopter().setTarget(event.getRawX(), event.getRawY());
                return true;
            }
        });
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        gameWorld.draw(canvas);
    }

    @Override
    public void update(float dt) {
        gameWorld.update(dt);
    }
}
