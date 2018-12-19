import javax.swing.*;

public class TesterGUI {
	private static JFrame myMain;
	
	public static void main(String[] args) {

	}
	
	private static void initiate() {
		makeWindow();
	}
	
	private static void makeWindow() {
		myMain = new JFrame();
		myMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myMain.setLocationByPlatform(true);
		myMain.setVisible(true);
	}
	
	//Adds the package and test selectors
	private static void addSelections() {
		
	}

}
