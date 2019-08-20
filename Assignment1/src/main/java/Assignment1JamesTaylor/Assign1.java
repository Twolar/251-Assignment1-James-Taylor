package Assignment1JamesTaylor;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Hello world!
 *
 */
public class Assign1 extends JFrame implements ActionListener{

    public Assign1(){
        // create the frame
        super("[Scribe]");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        // This creates the main menu bar
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

        // This creates a sub menu option "File" of the main menu bar 
        JMenu fileOption = new JMenu("File");
        menuBar.add(fileOption);

        // this create the sub menu for the option "File" in the main menu bar
        JMenu newOption = new JMenu("New");
        JMenu openOption = new JMenu("Open");
        JMenu saveOption = new JMenu("Save");
        fileOption.add(newOption);
        fileOption.add(openOption);
        fileOption.add(saveOption);

        // assign the menu bar to the window/frame
        this.setJMenuBar(menuBar);

        // Create text area
        JTextArea textArea;
        textArea = new JTextArea();
        this.add(textArea);

        // Make the window/frame visible.
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent event) {

    }
    public static void main( String[] args )
    {
        System.out.println( "Starting App..." );
        new Assign1();
    }
}
