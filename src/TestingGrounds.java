import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class TestingGrounds {

	public static void main(String[] args) throws FileNotFoundException {
		/*VerseReader vr = new VerseReader();
		//vr.printVerses();
		VersePackage vp = vr.get(2);
		//log(vp.numVerses());
		//log(vp.toString());
		Verse[] verses1 = vp.randomizeVerses();
		readVerses(verses1);
		log("");
		Verse[] verses2 = vp.randomizeVerses();
		readVerses(verses2);
		log("");
		Verse[] verses3 = vp.randomizeVerses();
		readVerses(verses3);
		Verse[] verses2 = vp.randomizeVerses();
		readVerses(verses1);
		readVerses(verses2);*/
		
		log("Emily/Emily: " + wordMatches("Emily", "Emily"));
		log("Emily/Cory: " + wordMatches("Emily", "Cory"));
		log("Emily/Emaly: " + wordMatches("Emily", "Emaly"));
		log("Emily/Emmmily: " + wordMatches("Emily", "Emmmily"));
		log("Emily/Amalie: " + wordMatches("Emily", "Amalie"));
		log("One/on: " + wordMatches("One", "on"));
		log("God/lord: " + wordMatches("God", "lord"));
		log("reconciliation/reckonsiliashun: " + wordMatches("reconciliation", "reckonsiliashun"));
	}
	
	public static void readVerses(Verse[] in) {
		for(int i = 0; i < in.length; i++) {
			log(in[i].toString());
		}
	}
	
	private static boolean arrayIsFull(String[] in) {
		boolean result = true;
		for(int i = 0; i < in.length; i++) {
			if(in[i] == null) {
				result = false;
			}
		}
		return result;
	}
	
	private static boolean wordMatches(String word1, String word2) {
		TreeMap<Character, Integer> w1 = makeTree(word1);
		TreeMap<Character, Integer> w2 = makeTree(word2);
		int difference = 0;
		Set<Character> w1s = w1.keySet();
		Iterator<Character> itr = w1s.iterator();
		Character curr = itr.next();
		while(itr.hasNext()) {
			if(w2.containsKey(curr))
				difference += Math.abs((w1.get(curr) - w2.get(curr)));
			else
				difference += w1.get(curr);
			curr = itr.next();
		}
		/*Set<Character> w2s = w2.keySet();
		if(!w2s.isEmpty()) {
			itr = w2s.iterator();
			curr  = itr.next();
			while(itr.hasNext()) {
				if(!w1.containsKey(curr))
					difference += w2.get(curr);
				curr = itr.next();
			}
		}*/
		double perDiff = (double) difference / (double) word1.length();
		//log("\tDiff: " + difference + " w1 len: " + word1.length() + " pDiff: " + perDiff);
		return perDiff < 0.25;
	}
	
	private static TreeMap<Character, Integer> makeTree(String in) {
		TreeMap<Character, Integer> theTree = new TreeMap<Character, Integer>();
		for(int i = 0; i < in.length(); i++) {
			char curr = in.charAt(i);
			if(Character.isAlphabetic(curr)) {
				int count = 1;
				if(theTree.containsKey(curr)) {
					count += theTree.get(curr);
					theTree.put(curr, count);
				}
				else {
					theTree.put(curr, count);
				}
			}
		}
		return theTree;
	}
	
	public static void log(Object text) {
		System.out.println(text.toString());
	}
}
