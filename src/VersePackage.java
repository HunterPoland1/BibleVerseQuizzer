import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class VersePackage {
	private ArrayList<Verse> myVerseList;
	private int myGroupNum;
	private Random myRand;
	
	public VersePackage() {
		myVerseList = new ArrayList<Verse>();
		myGroupNum = -1;
		myRand = new Random();
		if(!myVerseList.isEmpty()) {
			myGroupNum = myVerseList.get(0).getGroup();
		}
	}
	
	public VersePackage(ArrayList<Verse> vpIn) {
		myVerseList = vpIn;
		if(!vpIn.isEmpty()) {
			myGroupNum = myVerseList.get(0).getGroup();
		}
	}
	
	public int groupNum() {
		return myGroupNum;
	}
	
	public int numVerses() {
		return myVerseList.size();
	}
	
	public void add(Verse in) {
		if(in != null) {
			if(myGroupNum == -1) {
				myGroupNum = in.getGroup();
			}
			if(in.getGroup() == myGroupNum) {
				myVerseList.add(in);
			}
		}
	}
	
	public ArrayList<Verse> getVerses() {
		return myVerseList;
	}
	
	/*public Verse[] getVerses() {
		Verse[] versesOut = new Verse[myVerseList.size()];
		for(int i = 0; i < myVerseList.size(); i++) {
			versesOut[i] = myVerseList.get(i);
		}
		return versesOut;
	}*/
	
	public Verse[] randomizeVerses() {
		Verse[] versesOut = new Verse[myVerseList.size()];
		for(int i = 0; i < myVerseList.size(); i++) {
			versesOut[i] = myVerseList.get(i);
		}
		for(int i = myVerseList.size() - 1; i > 0; i--) {
			int theIndex = myRand.nextInt(i + 1);
			Verse temp = versesOut[i];
			versesOut[i] = versesOut[theIndex];
			versesOut[theIndex] = temp;
		}
		return versesOut;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[Group Number: " + myGroupNum + " Verses: ");
		for(Verse curr : myVerseList) {
			sb.append(curr.getReference() + " ");
		}
		sb.append("]");
		return sb.toString();
	}
	
	private boolean arrayIsFull(Verse[] in) {
		boolean result = true;
		for(int i = 0; i < in.length; i++) {
			if(in[i] == null) {
				result = false;
			}
		}
		return result;
	}
	
	private static void log(Object text) {
		System.out.println(text.toString());
	}
}
