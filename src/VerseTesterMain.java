import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class VerseTesterMain {
	private static double MAX_WORD_ERROR = 0.25;
	private static double VERSE_PERCENTAGE = 0.8;
	
	private static VerseReader myManager;
	private static String mySelection;
	private static int testNum;
	private static VersePackage myCurrVP;
	private static Scanner myIR;
	private static String myCurrCommand;
	
	public static void main(String[] args) throws FileNotFoundException {
		myIR = new Scanner(System.in);
		myManager = new VerseReader();
		log("Welcome to the EPICChurchTime verse package tester.");
		while(true) { //This will endlessly loop the prompt to take tests.
			log("Please enter the number of the verse package you want to test for: ");
			myCurrCommand = myIR.nextLine();
			while(!isNumeric(myCurrCommand)) {
				log("Invalid input. Please enter a number without any other characters.");
				myCurrCommand = myIR.nextLine();			
			}
			int test = Integer.parseInt(myCurrCommand);
			log("Your selection: " + test);
			myCurrVP = myManager.get(test);
			log("Please select a test.");
			log("#1: Location | #2: Text | #3: Simulation");
			mySelection = myIR.nextLine();
			processSelection(mySelection);
		}
	}
	
	private static boolean isNumeric(String in) {
		try {
			Integer.parseInt(in);
		}
		catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static void locationTest() {
		log("Location Test");
		log("For each of the following verses, type the number next to the correct reference.");
		Verse[] verses = myCurrVP.randomizeVerses();
		ArrayList<Verse> verses2 = myCurrVP.getVerses();
		StringBuilder verseOptions = new StringBuilder();
		for(int i = 0; i < verses2.size(); i++) {
			verseOptions.append((i + 1) + ") " + verses2.get(i).getReference() + "    ");
		}
		
		int score = 0;
		for(int i = 0; i < verses.length; i++) {
			log("");
			log("Verse " + (i + 1) + ": " + verses[i].getText());
			log(verseOptions.toString());
			log("Enter the number of the reference matching the text: ");
			
			int answer = myIR.nextInt();
			if(verses2.get(answer - 1).getText().equalsIgnoreCase(verses[i].getText())) {
				score++;
			}
		}
		log("");
		log("Your score: " + score + "/" + myCurrVP.numVerses());
		if(score > 2 && score < myCurrVP.numVerses()) {
			log("You passed! Way to go, bucko!");
		}
		else if(score <= 2) {
			log("Unfortunately, you did not pass. Hey, it happens. Study hard and try again sometime, ok champ?");
		}
		else {
			log("Wow, PERFECT SCORE! Are you just showing off?");
		}
	}
	
	public static void textTest() {
		log("Text Test");
		log("Type the text matching the reference given.");
		Verse[] verses = myCurrVP.randomizeVerses();
		String response;
		int score = 0;
		for(int i = 0; i < verses.length; i++) {
			log("");
			log((i + 1) + ". " + verses[i].getReference() + ": ");
			response = myIR.nextLine();
			if(textMatches(response, verses[i].getText()))
				score++;
		}
		log("");
		log("Your score: " + score + "/" + myCurrVP.numVerses());
		if(score > 2 && score < myCurrVP.numVerses()) {
			log("You passed! Way to go, bucko!");
		}
		else if(score <= 2) {
			log("Unfortunately, you did not pass. Hey, it happens. Study hard and try again sometime, ok champ?");
		}
		else {
			log("Wow, PERFECT SCORE! Are you just showing off?");
		}
	}
	
	private static boolean textMatches(String userResponse, String verseText) {
		int matches = 0;
		String[] verseWords = verseText.split(" ");
		String[] responseWords = userResponse.split(" ");
		int wordCount = verseWords.length;
		if(verseWords.length < responseWords.length) {
			String[] temp = verseWords;
			verseWords = responseWords;
			responseWords = temp;
		}
		
		for(int i = 0; i < verseWords.length; i++) {
			for(int j = 0; j < responseWords.length; j++) {
				if(wordMatches(verseWords[i], responseWords[j])) {
					matches++;
					break;
				}
			}
		}
		double perDiff = (double) matches / (double) wordCount;
		log(matches + " / " + wordCount + " = " + perDiff + ": " + (perDiff >= VERSE_PERCENTAGE));
		return perDiff >= VERSE_PERCENTAGE;
	}
	
	private static boolean wordMatches(String word1, String word2) {
		TreeMap<Character, Integer> w1 = makeWordTree(word1);
		TreeMap<Character, Integer> w2 = makeWordTree(word2);
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
		//log("\t" + word1 + ", " + word2 + " diff: " + difference + " w1 len: " + word1.length() + " pDiff: " + perDiff);
		return perDiff < MAX_WORD_ERROR;
	}
	
	/*private static TreeMap<String, Integer> makeVerseTree(String in) {
		String[] words = in.split(" ");
		TreeMap<String, Integer> theTree = new TreeMap<String, Integer>();
		String curr;
		for(int i = 0; i < words.length; i++) {
			curr = words[i];
			if(!theTree.containsKey(curr))
				theTree.put(curr, 1);
			else {
				int count = theTree.get(curr) + 1;
				theTree.put(curr, count);
			}
		}
		return theTree;
	}*/
	
	private static TreeMap<Character, Integer> makeWordTree(String in) {
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
	
	public static void simTest() {
		log("Simulation Test");
		log("For the situation given, type the number next to the best reference.");
		Verse[] verses = myCurrVP.randomizeVerses();
		ArrayList<Verse> verses2 = myCurrVP.getVerses();
		StringBuilder verseOptions = new StringBuilder();
		for(int i = 0; i < verses2.size(); i++) {
			verseOptions.append((i + 1) + ") " + verses2.get(i).getReference() + "    ");
		}
		
		int score = 0;
		for(int i = 0; i < verses.length; i++) {
			log("");
			log("Verse " + (i + 1) + ": " + verses[i].getRandomApp());
			log(verseOptions.toString());
			log("Enter the number of the reference which is the best response: ");
			
			int answer = myIR.nextInt();
			if(verses2.get(answer - 1).getExplicitApp().equalsIgnoreCase(verses[i].getExplicitApp()) || 
					verses2.get(answer - 1).getHypotheticalApp().equalsIgnoreCase(verses[i].getHypotheticalApp())) {
				score++;
			}
		}
		log("");
		log("Your score: " + score + "/" + myCurrVP.numVerses());
		if(score > 2 && score < myCurrVP.numVerses()) {
			log("You passed! Way to go, bucko!");
		}
		else if(score <= 2) {
			log("Unfortunately, you did not pass. Hey, it happens. Study hard and try again sometime, ok champ?");
		}
		else {
			log("Wow, PERFECT SCORE! Are you just showing off?");
		}
	}
	
	public static void processSelection(String in) {
		if(in.equalsIgnoreCase("1") || in.equalsIgnoreCase("test1") || in.equalsIgnoreCase("test 1")
				|| in.equalsIgnoreCase("location") || in.equalsIgnoreCase("reference")) {
			testNum = 1;
			locationTest();
		} else if(in.equalsIgnoreCase("2") || in.equalsIgnoreCase("test2") || in.equalsIgnoreCase("test 2")
				|| in.equalsIgnoreCase("text")) {
			testNum = 2;
			textTest();
		} else if(in.equalsIgnoreCase("3") || in.equalsIgnoreCase("test3") || in.equalsIgnoreCase("test 3")
				|| in.equalsIgnoreCase("simulation") || in.equalsIgnoreCase("application") || in.equalsIgnoreCase("app")) {
			testNum = 3;
			simTest();
		} else {
			testNum = -1;
			log("Invalid selection. Please enter one of the following options:");
			String retry = myIR.nextLine();
			processSelection(retry);
		}
	}
	
	public static void log(Object in) {
		System.out.println(in.toString());
	}
}
