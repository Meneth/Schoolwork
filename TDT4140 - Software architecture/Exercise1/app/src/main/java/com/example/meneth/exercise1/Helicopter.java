package com.example.meneth.exercise1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.TextView;

import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.math.BoundingBox;
import sheep.math.Vector2;

/**
 * Created by Meneth on 2016-02-07.
 */
public class Helicopter extends Sprite implements CollisionListener {
    private float[] direction;
    private static final float velocity = 150.0f;

    private final float width, height;
    private int frameIndex;
    private final int frameCount;
    private final Image[] frames;
    private float frameTime;

    public Helicopter(Image...frames) {
        super(frames[0]);
        width = frames[0].getWidth();
        height = frames[0].getHeight();
        setPosition((float) Math.random() * 500, (float) Math.random() * 1000);
        direction = new float[] {-1, -1};

        frameIndex = 0;
        frameCount = frames.length;
        this.frames = frames;
        frameTime = 0;
    }

    public void update(float dt) {
        super.update(dt);
        frameTime += dt;
        if (frameTime >= 0.1) {
            frameTime -= 0.1;
            frameIndex = (frameIndex + 1) % frameCount;
            setView(frames[frameIndex]);
        }

        setSpeed(direction[0] * velocity, direction[1] * velocity);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);

        // Turn around the helicopter if it hits the edge
        if (getX() - width / 2 < 0)
            direction[0] = Math.abs(direction[0]);
        if (getX() + width / 2 > canvas.getWidth())
            direction[0] = -Math.abs(direction[0]);

        if (getY() - height / 2 < 0)
            direction[1] = Math.abs(direction[1]);
        if (getY() + height / 2 > canvas.getHeight())
            direction[1] = -Math.abs(direction[1]);

        Paint paint = new Paint();
        paint.setTextSize(30);
        canvas.drawText("Position: " + (int) getX() + ", " + (int) getY(), 10, 25, paint);
    }

    /**
     * Directs the helicopter towards a given target
     * @param x The x coordinate of the target
     * @param y The y coordinate of the target
     */
    public void setTarget(float x, float y) {
        float dx = x - getX();
        float dy = y - getY();
        if (Math.abs(dx) > Math.abs(dy)) {
            direction[0] = Math.signum(dx);
            direction[1] = dy / Math.abs(dx);
        } else {
            direction[0] = dx / Math.abs(dy);
            direction[1] = Math.signum(dy);
        }
    }

    /**
     * Bounces the helicopter when it collides with something
     * @param sprite The helicopter
     * @param sprite2 What it is colliding with
     */
    @Override
    public void collided(Sprite sprite, Sprite sprite2) {
        ((Helicopter) sprite).handleCollision(sprite2);
        ((Helicopter) sprite2).handleCollision(sprite);
    }

    public void handleCollision(Sprite sprite) {
        float lowerEdge = getY() + getHeight() / 2;
        float upperEdge = getY() - getHeight() / 2;
        float rightEdge = getX() + getWidth() / 2;
        float leftEdge = getX() - getWidth() / 2;

        if (leftEdge < sprite.getX() && rightEdge > sprite.getX()) {
            // Colliding from above or below
            if (getY() > sprite.getY()) {
                direction[1] = Math.abs(direction[1]);
            } else {
                direction[1] = -Math.abs(direction[1]);
            }
        }
        else if (upperEdge < sprite.getY() && lowerEdge > sprite.getY()) {
            // Colliding from left or right
            if (getX() > sprite.getX()) {
                direction[0] = Math.abs(direction[0]);
            } else {
                direction[0] = -Math.abs(direction[0]);
            }
        } else {
            // Colliding with a corner
            // Simply move away from the center of what we collide with
            float dx = getX() - sprite.getX();
            float dy = getY() - sprite.getY();
            setTarget(getX() + dx, getY() + dy);
        }
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
