package interfaces;

import java.util.Comparator;

public class NamedComparator implements Comparator<Named> {

	public int compare(Named p1, Named p2) {
		String p1name = p1.getFamilyName()+" "+p1.getGivenName();
		String p2name = p2.getFamilyName()+" "+p2.getGivenName();
		return p1name.compareTo(p2name);
	}
}
