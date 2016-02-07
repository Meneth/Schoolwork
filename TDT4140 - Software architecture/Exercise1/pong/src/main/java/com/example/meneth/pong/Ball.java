package com.example.meneth.pong;

import android.graphics.Canvas;

import sheep.game.Sprite;

/**
 * Created by Meneth on 2016-02-07.
 */
public class Ball extends Token {
    private final float center;

    public Ball(float posX) {
        super(posX, 15, 15);
        center = posX;
        resetBall();
    }

    public void handleCollision(Sprite sprite) {
        float[] direction = getDirection();

        if (getX() > sprite.getX()) {
            direction[0] = 1;
        } else {
            direction[0] = -1;
        }
        // Bit of randomness to direction
        direction[1] += (Math.random() - 0.5) / 2;
        // Bit of randomness to velocity, biased towards slow increase in velocity
        setVelocity((float) (getVelocity() + 10 * (Math.random() - 1 / 3)));
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (getX() - getWidth() < 0) {
            getState().givePoint(GameState.Paddles.RIGHT);
            resetBall();
        }
        if (getX() + getWidth() > canvas.getWidth()) {
            getState().givePoint(GameState.Paddles.LEFT);
            resetBall();
        }
    }

    private void resetBall() {
        setPosition(center, 500);
        setDirection(new float[] {Math.random() > 0.5 ? 1 : -1, (float) Math.random()});
    }
}
