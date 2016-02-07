package com.example.meneth.pong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import sheep.game.State;

/**
 * Created by Meneth on 2016-02-08.
 */
public class GameOverState extends State {
    private final GameState.Paddles paddle;

    GameOverState(GameState.Paddles paddle) {
        this.paddle = paddle;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        switch (paddle) {
            case LEFT:
                canvas.drawText("Player 1 won!", Pong.maxX / 2, Pong.maxY / 2, paint);
                break;
            case RIGHT:
                canvas.drawText("Player 2 won!", Pong.maxX / 2, Pong.maxY / 2, paint);
                break;
        }
    }
}
