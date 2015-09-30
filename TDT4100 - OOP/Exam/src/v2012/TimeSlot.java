package v2012;

public class TimeSlot implements Comparable<TimeSlot> {
	private final DayTime start, end;
	private final String name;
	
	public TimeSlot(String name, int hour, int minute, int duration) {
		start = new DayTime(hour, minute);
		end = dayTime(minutes(start) + duration);
		this.name = name;
	}
	
	private static int minutes(DayTime time) {
		return time.hours * 60 + time.minutes;
	}
	
	private static DayTime dayTime(int minutes) {
		return new DayTime(minutes / 60, minutes % 60);
	}
	
	public DayTime getStartTime() {
		return start;
	}
	
	public DayTime getEndTime() {
		return end;
	}
	
	public int getDuration() {
		return minutes(end) - minutes(start);
	}
	
	public String toString() {
		return name + "@" + start + "-" + end;
	}
	
	public boolean contains(int hours, int minutes) {
		int t = minutes(new DayTime(hours, minutes));
		return t >= minutes(getStartTime()) && t < minutes(getEndTime());
	}
	
	public boolean overlaps(TimeSlot timeSlot) {
		DayTime start = timeSlot.getStartTime();
		DayTime end = timeSlot.getEndTime();
		return contains(start.hours, start.minutes) || contains(end.hours, end.minutes);
	}

	@Override
	public int compareTo(TimeSlot o) {
		int diff = minutes(getStartTime()) - minutes(o.getStartTime());
		if (diff > 0)
			return 1;
		else if (diff < 0)
			return -1;
		return minutes(getEndTime()) - minutes(o.getEndTime());
	}
}
