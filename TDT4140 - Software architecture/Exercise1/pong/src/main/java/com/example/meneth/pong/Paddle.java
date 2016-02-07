package com.example.meneth.pong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.math.Vector2;

/**
 * Created by Meneth on 2016-02-07.
 */
public class Paddle extends Token {
    private final int maxY;
    private int points = 0;

    public Paddle(float posX, int maxY) {
        super(posX, 20, 80);
        this.maxY = maxY;
    }

    public void moveTowards(float y) {
        if (y > getY() && getY() + getHeight() < maxY) {
            super.setDirection(new float[] {0, 1});
        } else if (y < getY() && getY() - getHeight() > 0) {
            super.setDirection(new float[] {0, -1});
        }
    }

    public void stopMovement() {
        super.setDirection(new float[] {0, 0});
    }

    @Override
    public void handleCollision(Sprite sprite) {
        // Simply ignore collisions
    }

    public void moveTowardsPrediction(Ball ball) {
        float dx;
        if (ball.getDirection()[0] == 1) {
            dx = getX() - ball.getX();
        } else {
            dx = getX() + ball.getX();
        }

        float dy = dx * ball.getDirection()[1];

        // This is intentionally wrong if the ball will hit a wall, to make the AI slightly dumber
        float destination = ball.getY() + dy;

        if (Math.abs(getY() - destination) < 10) {
            stopMovement();
        } else {
            moveTowards(destination);
        }
    }

    public void givePoint() {
        points++;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.WHITE);
        canvas.drawText("" + points, getX(), 40, paint);
    }

    public int getPoints() {
        return points;
    }
}
