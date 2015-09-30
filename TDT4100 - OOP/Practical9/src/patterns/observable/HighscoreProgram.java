package patterns.observable;

import java.util.Scanner;

public class HighscoreProgram implements ListListener<Integer> {
	private HighscoreList list;
	
	public void init() {
		list = new HighscoreList(5);
		list.addListener(this);
	}
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextInt()) {
			list.addResult(scanner.nextInt());
		}
		scanner.close();
	}

	@Override
	public void listChanged(ObservableList<Integer> observableList, int i) {
		System.out.println(observableList);
		System.out.println("Changed at " + i);
	}
	
	public static void main(String[] args) {
		HighscoreProgram program = new HighscoreProgram();
		program.init();
		program.run();
	}
}
