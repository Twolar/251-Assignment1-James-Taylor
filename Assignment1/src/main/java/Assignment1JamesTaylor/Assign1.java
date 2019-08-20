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
    private JMenuBar menuBar; 
    private JMenu fileOption, searchOption, viewOption, manageOption, helpOption;
    private JMenuItem newOption, saveOption, openOption, exitOption;
    public Assign1(){
        // create the frame
        super("[Scribe]");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        // This creates the main menu bar
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

        // This creates a sub menu option "File" of the main menu bar 
        fileOption = new JMenu("File");
        searchOption = new JMenu("Search");
        viewOption = new JMenu("View");
        manageOption = new JMenu("Manage");
        helpOption = new JMenu("Help");
        menuBar.add(fileOption);
        menuBar.add(searchOption);
        menuBar.add(viewOption);
        menuBar.add(manageOption);
        menuBar.add(helpOption);


        // this create the sub menu for the option "File" in the main menu bar
        newOption = new JMenuItem("New");
        openOption = new JMenuItem("Open");
        saveOption = new JMenuItem("Save");
        exitOption = new JMenuItem("Exit");
        fileOption.add(newOption);
        fileOption.add(openOption);
        fileOption.add(saveOption);
        fileOption.add(exitOption);


        // assign the menu bar to the window/frame
        this.setJMenuBar(menuBar);

        //Action listners
        exitOption.addActionListener(this);

        // Create text area
        JTextArea textArea;
        textArea = new JTextArea();
        this.add(textArea);

        // Make the window/frame visible.
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent event) {
        JComponent source = (JComponent) event.getSource();
        if(source == exitOption){
            System.exit(0);
        }
    }
    public static void main( String[] args )
    {
        System.out.println( "Starting App..." );
        new Assign1();
    }
}
