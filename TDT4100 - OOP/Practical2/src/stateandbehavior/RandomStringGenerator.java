package stateandbehavior;

import java.util.Random;

public class RandomStringGenerator {
	String stringTable = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmopqrstuvwxyz1234567890";
	int stringLength = stringTable.length() - 1;
	
	Random random = new Random();
	public String getRandomString() {
		String randomString = "";
		while (random.nextInt(10) < 9) {
			randomString += stringTable.charAt(random.nextInt(stringLength));
		}
		return randomString;
	}
	public String getRandomString(int length) {
		String randomString = "";
		while (length > 0) {
			randomString += stringTable.charAt(random.nextInt(stringLength));
			length--;
		}
		return randomString;
	}
	public static void main(String[] args) {
		RandomStringGenerator generator = new RandomStringGenerator();
		System.out.println(generator.getRandomString(32));
		System.out.println(generator.getRandomString());
		System.out.println(generator.getRandomString());
		System.out.println(generator.getRandomString());
		System.out.println(generator.getRandomString());
		System.out.println(generator.getRandomString());
	}
}
