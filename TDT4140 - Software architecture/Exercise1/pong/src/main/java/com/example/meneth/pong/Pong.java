package com.example.meneth.pong;
/**
 * Created by Meneth on 2016-02-07.
 */
import android.graphics.Point;
import android.os.Bundle;
import android.app.Activity;
import android.view.Display;

import sheep.game.Game;

public class Pong extends Activity {
    public static int maxX, maxY;

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create the game.
        Game game = new Game(this, null);
        // Push the main state.
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int topBarSize = 72; // Found absolutely no way to determine this dynamically
        maxX = size.x;
        maxY = size.y - topBarSize;

        game.pushState(new GameState());
        // View the game.
        setContentView(game);
    }
}