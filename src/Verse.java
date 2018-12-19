import java.util.Random;

public class Verse {
	private int myGroup;
	private String myReference;
	private String myText;
	private String myExplicitApp;
	private String myHypotheticalApp;
	private Random myRand;
	
	public Verse(int group, String ref, String text, String exApp, String hypApp) {
		setGroup(group);
		setReference(ref);
		setText(text);
		setExplicitApp(exApp);
		setHypotheticalApp(hypApp);
		myRand = new Random();
	}

	public int getGroup() {
		return myGroup;
	}

	public void setGroup(int myGroupNum) {
		this.myGroup = myGroupNum;
	}

	public String getReference() {
		return myReference;
	}

	public void setReference(String myReference) {
		this.myReference = myReference;
	}

	public String getText() {
		return myText;
	}

	public void setText(String myText) {
		this.myText = myText;
	}

	public String getExplicitApp() {
		return myExplicitApp;
	}

	public void setExplicitApp(String myExplicitApp) {
		this.myExplicitApp = myExplicitApp;
	}

	public String getHypotheticalApp() {
		return myHypotheticalApp;
	}

	public void setHypotheticalApp(String myHypotheticalApp) {
		this.myHypotheticalApp = myHypotheticalApp;
	}
	
	public String getRandomApp() {
		String theApp;
		int choice = myRand.nextInt(2);
		if(choice == 0) {
			theApp = myExplicitApp;
		} else {
			theApp = myHypotheticalApp;
		}
		return theApp;
	}
	
	public String toString() {
		return "[Group#: " + myGroup + ", Reference: " + myReference + ", Text: " + 
				myText + ", Exp. App: " + myExplicitApp + ", Hypo. App: " + myHypotheticalApp + " ]";
	}
}
