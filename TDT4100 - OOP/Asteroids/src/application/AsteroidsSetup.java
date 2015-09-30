package application;

import javafx.scene.paint.Color;

public class AsteroidsSetup extends Asteroids {
	public void init() {
		super.init();
		Asteroid a1 = createAsteroid();
		Asteroid a2 = createAsteroid();
		Asteroid a3 = createAsteroid();
		Asteroid a4 = createAsteroid();
		add(a1, getPrefWidth() / 2, getPrefHeight() / 2);
		add(a2, getPrefWidth() / 4, getPrefHeight() / 2);
		add(a3, getPrefWidth() / 2, getPrefHeight() / 4);
		add(a4, getPrefWidth() / 4, getPrefHeight() / 4);
	}
	
	public Asteroid createAsteroid() {
		Asteroid a = new Asteroid(1, 1.2 + Math.random() / 2, 0.25, 7 + (int) (5 * Math.random()));
		a.setFill(Color.WHITE);
		return a;
	}
	
	public static void main(String[] args) {
		AsteroidsProgram.main(new String[]{application.AsteroidsSetup.class.getName()});
	}
}
