package interfaces;

public class Person1 implements Named {
	private String givenName;
	private String familyName;
	public Person1(String givenName, String familyName) {
		this.givenName = givenName;
		this.familyName = familyName;
	}

	@Override
	public String getGivenName() {
		return givenName;
	}

	@Override
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	@Override
	public String getFamilyName() {
		return familyName;
	}

	@Override
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}


	@Override
	public String getFullName() {
		return givenName + " " + familyName;
	}

	@Override
	public void setFullName(String fullName) {
		String[] split = fullName.split(" "); // The spec says nothing about how names are formatted, so a very basic model is used
		if (split.length != 2) {
			throw new IllegalArgumentException("Invalid name!");
		}
		givenName = split[0];
		familyName = split[1];
	}
}
