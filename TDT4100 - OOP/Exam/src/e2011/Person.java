package e2011;

import java.util.Date;

public class Person {
	private Date birthday;
	private final boolean male;
	private String ssn;
	private final int[] f = new int[] {3, 7, 6, 1, 8, 9, 4, 5, 2};
	private final int[] g = new int[] {5, 4, 3, 2, 7, 6, 5, 4, 3, 2};
	
	public Person(boolean male) {
		this.male = male;
	}
	
	public boolean isMale() {
		return male;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public String getSSN() {
		return ssn;
	}
	
	public void setSSN(String ssn) {
		if (ssn.length() != 11)
			throw new IllegalArgumentException("Invalid SSN length. Must be 11 characters long.");
		for (char c : ssn.toCharArray()) {
			if (c < '0' || c > '9')
				throw new IllegalArgumentException("SSN must consist only of numbers.");
		}
		Date birth = getBirthday();
		String birthID = String.format("%02d%02d%02d", birth.getDay(), birth.getMonth(), birth.getYear());
		if (!birthID.equals(ssn.substring(0, 6)))
			throw new IllegalStateException("SSN does not correspond with birthday. Expected " + birthID);
		if ((ssn.charAt(8) - '0') % 2 == 1 ^ male)
			throw new IllegalArgumentException("SSN does not correspond with gender.");
		int k1 = 0, k2 = 0;
		for (int i = 0; i < f.length; i++) {
			k1 += f[i] * (ssn.charAt(i) - '0');
			k2 += g[i] * (ssn.charAt(i) - '0');
		}
		k2 += g[9] * ssn.charAt(9);
		k1 = 11 - k1 % 11;
		k2 = 11 - k2 % 11;
		if (k1 != (ssn.charAt(9) - '0'))
			throw new IllegalArgumentException("The first control number is incorrect. Expected " + k1);
		if (k2 != (ssn.charAt(10) - '0'))
			throw new IllegalArgumentException("The second control number is incorrect. Expected " + k2);
		this.ssn = ssn;
	}
	
	public static void main(String[] args) {
		System.out.println(4/3);
	}
}
