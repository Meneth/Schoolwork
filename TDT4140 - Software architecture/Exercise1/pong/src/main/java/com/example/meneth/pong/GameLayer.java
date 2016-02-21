package com.example.meneth.pong;

import android.graphics.Canvas;

import sheep.collision.CollisionLayer;
import sheep.game.Layer;
import sheep.graphics.Image;
import sheep.math.BoundingBox;

/**
 * Created by Meneth on 2016-02-07.
 */
public class GameLayer extends Layer {
    private Paddle paddle;
    private Paddle paddle2;
    private static Ball ball;
    private CollisionLayer collisionLayer;

    private Token[] tokens;

    public GameLayer() {
        collisionLayer = new CollisionLayer();

        paddle = new Paddle(150, Pong.maxY);
        paddle2 = new Paddle(Pong.maxX - 150, Pong.maxY);

        tokens = new Token[] {paddle, paddle2, getBall()};

        for (Token token : tokens ) {
            collisionLayer.addSprite(token);
            token.addCollisionListener(token);
        }
    }

    @Override
    public void update(float dt) {
        paddle.update(dt);
        paddle2.moveTowardsPrediction(ball);
        paddle2.update(dt);
        getBall().update(dt);
        collisionLayer.update(dt);
    }

    @Override
    public void draw(Canvas canvas, BoundingBox boundingBox) {
        paddle.draw(canvas);
        paddle2.draw(canvas);
        getBall().draw(canvas);
    }

    public Paddle getPaddle() {
        return paddle;
    }
    public Paddle getPaddle2() {
        return paddle2;
    }

    // Singleton pattern for this. Doesn't make much sense, but there was no sensible place to use it
    public static Ball getBall() {
        if (ball == null)
            ball = new Ball((float) Pong.maxX / 2);
        return ball;
    }

    public Token[] getTokens() {
        return tokens;
    }
}
