package encapsulation;

import java.util.Random;

public class RandomStringGenerator {
	private static final String stringTable = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmopqrstuvwxyz1234567890";
	private static final int stringLength = stringTable.length() - 1;
	private static Random random = new Random();
	
	public static String getRandomString() {
		String randomString = "";
		while (random.nextInt(10) < 9) {
			randomString += stringTable.charAt(random.nextInt(stringLength));
		}
		return randomString;
	}
	public static String getRandomString(int length) {
		String randomString = "";
		while (length > 0) {
			randomString += stringTable.charAt(random.nextInt(stringLength));
			length--;
		}
		return randomString;
	}
	public static void main(String[] args) {
		System.out.println(RandomStringGenerator.getRandomString(32));
		System.out.println(RandomStringGenerator.getRandomString());
		System.out.println(RandomStringGenerator.getRandomString());
		System.out.println(RandomStringGenerator.getRandomString());
		System.out.println(RandomStringGenerator.getRandomString());
		System.out.println(RandomStringGenerator.getRandomString());
	}
}
