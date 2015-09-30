package objectstructures;

import java.util.ArrayList;

public class Person {
	private final String name;
	private final char gender;
	private Person mother;
	private Person father;
	private ArrayList<Person> children;
	
	public Person(String name, char gender) {
		children = new ArrayList<Person>();
		this.name = name;
		if (gender != 'F' && gender != 'M') {
			throw new IllegalArgumentException("Not a valid gender");
		}
		this.gender = gender;
	}
	
	public Person getMother() {
		return mother;
	}
	public void setMother(Person mother) {
		if (mother == this) {
			throw new IllegalArgumentException("Can't be your own mother.");
		}
		Person originalMother = getMother();
		this.mother = mother;
		if (originalMother != null) {
			originalMother.removeChild(this);;
		}
		if (mother != null) {
			if (mother.getGender() != 'F') {
				throw new IllegalArgumentException("Cannot set a man as the person's mother.");
			}
			mother.addChild(this);
		}
	}
	public Person getFather() {
		return father;
	}
	public void setFather(Person father) {
		if (father == this) {
			throw new IllegalArgumentException("Can't be your own father.");
		}
		Person originalFather = getFather();
		this.father = father;
		if (originalFather != null) {
			originalFather.removeChild(this);;
		}
		if (father != null) {
			if (father.getGender() != 'M') {
				throw new IllegalArgumentException("Cannot set a woman as the person's father.");
			}
			father.addChild(this);
		}
	}
	public Person getChild(int n) {
		return children.get(n);
	}
	public int getChildCount() {
		return children.size();
	}
	public void addChild(Person child) {
		if (child == this) {
			throw new IllegalArgumentException("Can't be your own child.");
		}
		if (children.contains(child)) {
			return; // No need to add if already a child
		}
		children.add(child);
		if (child.getFather() != this && child.getMother() != this) {
			if (getGender() == 'M') {
				child.setFather(this);
			} else {
				child.setMother(this);
			}
		}
	}
	public void removeChild(Person child) {
		children.remove(child);
		if (getGender() == 'F') {
			child.setMother(null);
		} else {
			child.setFather(null);
		}
	}
	public String getName() {
		return name;
	}
	public char getGender() {
		return gender;
	}
	public String toString() {
		return name + ", " + gender;
	}
}
