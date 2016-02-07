package com.example.meneth.exercise1;

import android.os.Bundle;
import android.app.Activity;

import sheep.game.Game;

public class Sprites extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create the game.
        Game game = new Game(this, null);
        // Push the main state.
        game.pushState(new SpritesGameState());
        // View the game.
        setContentView(game);
    }
}
