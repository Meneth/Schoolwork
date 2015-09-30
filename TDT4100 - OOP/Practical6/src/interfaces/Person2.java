package interfaces;

public class Person2 implements Named {
	private String fullName;
	
	public Person2(String name) {
		fullName = name;
	}

	@Override
	public String getGivenName() {
		return fullName.split(" ")[0];
	}

	@Override
	public void setGivenName(String givenName) {
		fullName = givenName + " " + getFamilyName();
	}

	@Override
	public String getFamilyName() {
		return fullName.split(" ")[1];
	}

	@Override
	public void setFamilyName(String familyName) {
		fullName = getGivenName() + " " + familyName;
	}

	@Override
	public String getFullName() {
		return fullName;
	}

	@Override
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
