import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class VerseReader {
	private String VERSE_FILE = "textDocs/verses.txt";
	private static Scanner verseFileReader;
	private static ArrayList<VersePackage> verseList;
	//private static ArrayList<Verse> verseList;
	
	public VerseReader() throws FileNotFoundException {
		verseFileReader = new Scanner(new File(VERSE_FILE));
		verseList = new ArrayList<VersePackage>();
		loadVerses();
	}
	
	private static void loadVerses() {
		int numGroups = 0;
		ArrayList<Verse> allVerses = new ArrayList<Verse>();
		while(verseFileReader.hasNextLine()) {
			String current = verseFileReader.nextLine();
			String[] elements = current.split("\\|");
			int tempGroup = Integer.parseInt(elements[0]);
			if(tempGroup > numGroups) {
				numGroups = tempGroup;
			}
			Verse aVerse = new Verse(tempGroup, elements[1], elements[2], elements[3], elements[4]);
			allVerses.add(aVerse);
		}
		
		for(int i = 0; i < numGroups; i++) {
			verseList.add(new VersePackage());
		}
		
		for(Verse nextVerse : allVerses) {
			verseList.get(nextVerse.getGroup() - 1).add(nextVerse);
		}
	}
	
	public VersePackage get(int groupNum) {
		if(!containsPackage(groupNum)) {
			throw new IllegalArgumentException();
		}
		VersePackage vp = null;
		for(VersePackage currVP : verseList) {
			if(currVP.groupNum() == groupNum) { //currVP.getVerses().get(0).getGroup() == groupNum) {
				vp = currVP;
			}
		}
		return vp;
	}
	
	public void printVerses() {
		for(VersePackage currVP : verseList) {
			ArrayList<Verse> currVerseSet = currVP.getVerses();
			for(Verse currVerse : currVerseSet) {
				System.out.println(currVerse.toString());
			}
		}
	}
	
	public boolean containsPackage(int vp) {
		if(verseList.size() >= vp) {
			return true;
		}
		return false;
	}
}
