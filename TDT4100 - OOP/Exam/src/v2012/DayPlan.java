package v2012;

import java.util.Collection;
import java.util.TreeSet;

public class DayPlan {
	private TreeSet<TimeSlot> plan = new TreeSet<TimeSlot>();
	
	public void addTimeSlot(TimeSlot timeSlot) {
		plan.add(timeSlot);
	}
	
	public void removeTimeSlot(TimeSlot timeSlot) {
		plan.remove(timeSlot);
	}
	
	public TimeSlot getTimeSlotAt(int hours, int minutes) {
		for (TimeSlot ts : plan) {
			if (ts.contains(hours, minutes))
				return ts;
		}
		return null;
	}
	
	public boolean containsOverlapping() {
		for (TimeSlot ts : plan) {
			TimeSlot next = plan.higher(ts);
			if (next != null && ts.overlaps(next))
				return true;
		}
		return false;
	}
	
	public Collection<TimeSlot> getFreeTime() {
		return null;
	}
}
