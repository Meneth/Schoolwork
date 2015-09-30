package k2012;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class WordList {
	Collection<String> words;
	
	public boolean containsWord(String word) {
		return words.contains(word);
	}
	
	public Collection<String> getWordsStartingWith(String prefix) {
		Collection<String> c = new HashSet<>();
		for(String s : words) {
			if (s.startsWith(prefix)) {
				c.add(s);
			}
		}
		return c;
	}
	
	public boolean addWord(String word) {
		word = word.trim();
		if (word.equals(""))
			return false;
		if (words.contains(word))
			return false;
		return words.add(word);
	}
	
	public boolean removeWord(String word) {
		return words.remove(word);
	}
	
	public boolean removeWordsStartingWith(String prefix) {
		Collection<String> c = getWordsStartingWith(prefix);
		return words.removeAll(c);
	}
	
	public static String getPrefix(String word, String suffix) {
		int i = word.lastIndexOf(suffix);
		if (i != -1) {
			return word.substring(0, i);
		}
		return null;
	}
	
	public boolean hasSuffixes(String prefix, List<String> suffixes) {
		for(String s : suffixes) {
			if(!words.contains(prefix+s))
				return false;
		}
		return true;
	}
	
	public List<String> findPrefixes(List<String> suffixes) {
		List<String> l = new ArrayList<>();
		String s = suffixes.get(0);
		for (String word : words) {
			word = getPrefix(word, s);
			if (word != null && hasSuffixes(word, suffixes))
				l.add(word);
		}
		return l;
	}
}
