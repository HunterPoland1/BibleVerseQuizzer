import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.io.PrintStream;

public class TesterGUI {
    private static JTextArea outputTextArea;
    private static JTextArea verseInputTextArea;
    private static PrintStream originalOut;

    public static void main(String[] args) {
        // Start the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        // Create the main JFrame
        JFrame frame = new JFrame("Verse Tester GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(515, 400);

        // Create the main panel to hold components
        JPanel panel = new JPanel();
        frame.add(panel);

        // Place components on the panel
        placeComponents(panel);

        // Make the frame visible
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        // Set absolute positioning for components
        panel.setLayout(null);

        // Text area for input verse
        verseInputTextArea = new JTextArea();
        verseInputTextArea.setBounds(20, 20, 460, 80);
        verseInputTextArea.setEditable(false); // Prevent user input
        panel.add(verseInputTextArea);

        // Buttons for different tests
        JButton textTestButton = new JButton("Text Test");
        textTestButton.setBounds(20, 120, 140, 30);
        panel.add(textTestButton);

        JButton simulationTestButton = new JButton("Simulation Test");
        simulationTestButton.setBounds(180, 120, 140, 30);
        panel.add(simulationTestButton);

        JButton locationTestButton = new JButton("Location Test");
        locationTestButton.setBounds(340, 120, 140, 30);
        panel.add(locationTestButton);

        // Text area for output
        outputTextArea = new JTextArea();
        outputTextArea.setBounds(20, 170, 460, 100);
        outputTextArea.setEditable(true); // Allow output to be edited
        panel.add(outputTextArea);

        // Button to submit answer
        JButton submitButton = new JButton("Submit Answer");
        submitButton.setBounds(340, 290, 140, 30);
        panel.add(submitButton);

        // Action listeners for test buttons
        simulationTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to start the Simulation Test
            }
        });

        locationTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to start the Location Test
            }
        });
    }

    // Getter for verse input text area
    public static JTextArea getVerseInputTextArea() {
        return verseInputTextArea;
    }

    // Custom output stream class
    private static class CustomOutputStream extends OutputStream {
        private JTextArea textArea;

        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) {
            // Append text to the text area
            textArea.append(String.valueOf((char) b));
            // Set caret position to end of text area
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }
}

