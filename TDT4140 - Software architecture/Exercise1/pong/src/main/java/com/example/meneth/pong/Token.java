package com.example.meneth.pong;

import android.graphics.Canvas;

import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * Created by Meneth on 2016-02-07.
 */
public class Token extends Sprite implements CollisionListener {
    private float[] direction;
    private float velocity = 200.0f;

    private GameState state;

    private final float width, height;

    public Token(float posX, float width, float height) {
        super(new Image(R.drawable.token));
        setScale(width, height);
        this.width = getWidth();
        this.height = getHeight();
        setPosition(posX, 520);
        direction = new float[] {0, 0};
    }

    public void update(float dt) {
        super.update(dt);

        setSpeed(direction[0] * velocity, direction[1] * velocity);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);

        // Turn around the helicopter if it hits the edge
        if (getX() - width < 0)
            direction[0] = Math.abs(direction[0]);
        if (getX() + width > canvas.getWidth())
            direction[0] = -Math.abs(direction[0]);

        if (getY() - height < 0)
            direction[1] = Math.abs(direction[1]);
        if (getY() + height > canvas.getHeight())
            direction[1] = -Math.abs(direction[1]);
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
        ((Token) sprite).handleCollision(sprite2);
        ((Token) sprite2).handleCollision(sprite);
    }

    public void handleCollision(Sprite sprite) {
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setDirection(float[] direction) {
        this.direction = direction;
    }

    public float[] getDirection() {
        return direction;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public GameState getState() {
        return state;
    }
}
