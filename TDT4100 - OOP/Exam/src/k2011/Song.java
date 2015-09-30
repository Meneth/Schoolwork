package k2011;

public class Song {
	private static final String vowels = "aeiouyæøå";
	private final String song;
	
	public Song(String song) {
		this.song = song;
	}
	
	public static boolean isVowel(char c) {
		return vowels.indexOf(Character.toLowerCase(c)) != -1;
	}
	
	public static String computeVerse(String originalVerse, char v) {
		char[] verse = originalVerse.toCharArray();
		for (char c : verse) {
			if (isVowel(c) && !Character.isUpperCase(c))
				c = v;
			if (isVowel(c) && Character.isUpperCase(c))
				c = Character.toUpperCase(v);
		}
		return new String(verse);
	}
	
	public static void writeSong(String originalVerse, char[] vowels) {
		for (char c : vowels)
			System.out.println(computeVerse(originalVerse, c));
	}
	
	public void writeSong(String vowels) {
		writeSong(song, vowels.toCharArray());
	}
}
