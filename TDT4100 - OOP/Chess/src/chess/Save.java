package chess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
	public static void save(String log, String target) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(target + ".txt"));
			writer.write(log);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Game load(String target) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(target + ".txt"));
			String s = reader.readLine();
			s = s.substring(1, s.length() - 1); // Gets rid of surrounding brackets
			String[] moves = s.split(", ");
			Game game = new Game(); // New board
			for (String move : moves) {
				game.move(move.charAt(1)-'0', move.charAt(3)-'0', move.charAt(5)-'0', move.charAt(7)-'0');
			}
			reader.close();
			return game;
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Loading of file failed.");
		}
	}
}
