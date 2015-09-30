package application;
	
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;


public class Oving2 extends Application {
	private Stage primaryStage;
	private static final Pattern roomPattern = Pattern.compile("[\\w- ]{1,}\\d{1,}");
	private static final Pattern timePattern = Pattern.compile("[0-2][0-9]:[0-5][0-9]");
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setController(this);
	        fxmlLoader.setLocation(getClass().getResource("Oving2.fxml"));
	        Parent root = (Parent) fxmlLoader.load(this.getClass().getResourceAsStream("Oving2.fxml"));
	        primaryStage.setScene(new Scene(root));
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML // Purpose left out since no validation
	private TextField room, date, from, to, repeat, end;
	
	@FXML
	public void save(ActionEvent event) {
		if (validateState()) {
			primaryStage.close();
		} else {
			highlightErrors();
		}
	}
	
	private void highlightErrors() {
		Date start = null;
		if (roomMatches(room)) valid(room);
		else invalid(room);
		if (timeMatches(from)) valid(from);
		else invalid(from);
		if (timeMatches(to)) valid(to);
		else invalid(to);
		if (timeMatches(from) && timeMatches(to))
			if (from.getText().compareTo(to.getText()) >= 0) {
				invalid(to); invalid(from);
			}
		try {
			start = dateFormat.parse(date.getText());
			valid(date);
		} catch (ParseException e) {
			invalid(date);
		}
		try {
			int rep = Integer.parseInt(repeat.getText());
			valid(repeat);
			if (rep > 0) {
				try {
					Date ends = dateFormat.parse(end.getText());
					valid(end);
					if (!ends.after(start)) { invalid(end); invalid(date); }
				} catch (ParseException e) {
					invalid(end);
				} // Not a valid date
			}
		} catch (NumberFormatException e) {
			invalid(repeat);
		} catch (NullPointerException e) {
			
		}
	}
	
	private static void invalid(TextField field) { field.getStyleClass().add("invalid"); }
	private static void valid(TextField field) { field.getStyleClass().removeAll(Collections.singleton("invalid")); }
	
	private boolean validateState() {
		Date start;
		if (!roomMatches(room))
			return false;
		if (!timeMatches(from) || !timeMatches(to))
			return false;
		if (from.getText().compareTo(to.getText()) >= 0)
			return false; // Can't end before it starts
		try {
			start = dateFormat.parse(date.getText());
		} catch (ParseException e) {
			return false; // Not a valid date
		}
		try {
			int rep = Integer.parseInt(repeat.getText());
			if (rep > 0) {
				try {
					Date ends = dateFormat.parse(end.getText());
					if (!ends.after(start))
						return false; // Can't end before it starts
				} catch (ParseException e) {
					return false;
				} // Repetition not filled out, not an int, or not a valid end date
			}
		} catch (NumberFormatException e1) {
			return false;
		}
		return true;
	}

	private static boolean roomMatches (TextField room) {
		return roomPattern.matcher(room.getText()).matches();
	}
	
	private static boolean timeMatches (TextField time) {
		return timePattern.matcher(time.getText()).matches()
				&& Integer.parseInt(time.getText().substring(0, 2)) < 24;
	}
	
	@FXML
	public void discard(ActionEvent event) {
		primaryStage.close();
	}
	
	@FXML
	public void toggleRepeat() {
		try {
			if (Integer.parseInt(repeat.getText()) > 0)
				end.setDisable(false);
			else end.setDisable(true);
		} catch (NumberFormatException e) {
			end.setDisable(true);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
