package com.example.meneth.pong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import sheep.game.State;
import sheep.game.World;
import sheep.input.TouchListener;

/**
 * Created by Meneth on 2016-02-07.
 */
public class GameState extends State {
    private World gameWorld;
    private GameLayer gameLayer;

    public GameState() {
        gameWorld = new World();
        gameLayer = new GameLayer();
        for (Token token : gameLayer.getTokens()) {
            token.setState(this);
        }
        gameWorld.addLayer(gameLayer);
        // Observer pattern used here
        this.addTouchListener(new TouchListener() {
            @Override
            public boolean onTouchDown(MotionEvent motionEvent) {
                gameLayer.getPaddle().moveTowards(motionEvent.getY());
                return true;
            }

            @Override
            public boolean onTouchUp(MotionEvent motionEvent) {
                gameLayer.getPaddle().stopMovement();
                return false;
            }

            @Override
            public boolean onTouchMove(MotionEvent motionEvent) {
                gameLayer.getPaddle().moveTowards(motionEvent.getY());
                return true;
            }
        });
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        gameWorld.draw(canvas);
    }

    @Override
    public void update(float dt) {
        gameWorld.update(dt);
    }

    public enum Paddles { LEFT, RIGHT };
    public void givePoint(Paddles paddle) {
        switch (paddle) {
            case LEFT:
                gameLayer.getPaddle().givePoint();
                if (gameLayer.getPaddle().getPoints() == 21) {
                    getGame().pushState(new GameOverState(Paddles.LEFT));
                }
                break;
            case RIGHT:
                gameLayer.getPaddle2().givePoint();
                if (gameLayer.getPaddle2().getPoints() == 21) {
                    getGame().pushState(new GameOverState(Paddles.RIGHT));
                }
                break;
        }
    }
}
