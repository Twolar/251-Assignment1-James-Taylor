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

        // Create sub menu option "File" of the main menu bar 
        JMenu fileOption = new JMenu("File");
        menuBar.add(fileOption);

        // Create sub menu option "Search" of the main menu bar
        JMenu searchOption = new JMenu("Search");
        menuBar.add(searchOption);

        // Create sub menu option "About" of the main menu bar
        JMenu aboutOption = new JMenu("About");
        menuBar.add(aboutOption);

        // Create sub menu option "exit" of the main menu bar
        JMenu exitOption = new JMenu("Exit");
        menuBar.add(exitOption);


        // this create the sub menu for the option "File" in the main menu bar
        JMenu newOption = new JMenu("New");
        JMenu openOption = new JMenu("Open");
        JMenu saveOption = new JMenu("Save");
        JMenu printOption = new JMenu("Print");
        fileOption.add(newOption);
        fileOption.add(openOption);
        fileOption.add(saveOption);
        fileOption.add(printOption);


        // Create and add the text area
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
