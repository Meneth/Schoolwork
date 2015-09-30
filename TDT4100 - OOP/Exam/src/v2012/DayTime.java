package v2012;

public class DayTime {

	public final int hours, minutes;

	public DayTime(int hours, int minutes) {
		this.hours = hours;
		this.minutes = minutes;
	}

	public String toString() {
		return String.format("%02d:%02d", hours, minutes);
	}
}