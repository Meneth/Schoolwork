package com.example.meneth.exercise1;

import android.graphics.Canvas;

import sheep.collision.CollisionLayer;
import sheep.collision.CollisionListener;
import sheep.game.Layer;
import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.math.BoundingBox;

/**
 * Created by Meneth on 2016-02-07.
 */
public class SpritesGameLayer extends Layer {
    private Helicopter helicopter;
    private Helicopter helicopter2;
    private CollisionLayer collisionLayer;

    public SpritesGameLayer() {
        collisionLayer = new CollisionLayer();

        //helicopter = new Helicopter(new Image(R.drawable.helicopter_1));
        Image[] frames = {new Image(R.drawable.helicopter_1), new Image(R.drawable.helicopter_2),
                new Image(R.drawable.helicopter_3), new Image(R.drawable.helicopter_4)};
        helicopter = new Helicopter(frames);
        helicopter2 = new Helicopter(frames);

        collisionLayer.addSprite(helicopter);
        collisionLayer.addSprite(helicopter2);

        helicopter.addCollisionListener(helicopter);
        helicopter2.addCollisionListener(helicopter2);
    }

    @Override
    public void update(float dt) {
        helicopter.update(dt);
        helicopter2.update(dt);
        collisionLayer.update(dt);
    }

    @Override
    public void draw(Canvas canvas, BoundingBox boundingBox) {
        helicopter.draw(canvas);
        helicopter2.draw(canvas);
    }

    public Helicopter getHelicopter() {
        return helicopter;
    }
}
