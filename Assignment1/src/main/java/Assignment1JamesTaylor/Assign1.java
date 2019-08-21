package Assignment1JamesTaylor;

import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import java.awt.event.*;

/**
 * Hello world!
 *
 */
public class Assign1 extends JFrame implements ActionListener{
    private JMenuBar menuBar; 
    private JMenu fileOption, editOption, searchOption, aboutOption;
    private JMenuItem newOption, saveOption, openOption, printOption, exitOption, selectOption, copyOption, pasteOption, cutOption, timeOption, infoOption;
    private JFrame popUp;
    public Assign1(){
        // create the frame
        super("[Scribe]");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        
        // This creates the main menu bar
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

        // This creates a sub menu option "File" of the main menu bar 
        fileOption = new JMenu("File");
        editOption = new JMenu("Edit");
        searchOption = new JMenu("Search");
        aboutOption = new JMenu("About");

		JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        menuBar.add(fileOption);
        menuBar.add(editOption);
        menuBar.add(searchOption);
        menuBar.add(aboutOption);

        // this create the sub menu for the option "File" in the main menu bar
        newOption = new JMenuItem("New");
        openOption = new JMenuItem("Open");
        saveOption = new JMenuItem("Save");
        printOption = new JMenuItem("Print");
        exitOption = new JMenuItem("Exit");

        fileOption.add(newOption);
        fileOption.add(openOption);
        fileOption.add(saveOption);
        fileOption.add(printOption);
        fileOption.add(exitOption);

        // this create the sub menu for the option "Edit" in the main menu bar
        selectOption = new JMenuItem("Select");
        copyOption = new JMenuItem("Copy");
        pasteOption = new JMenuItem("Paste");
        cutOption = new JMenuItem("Cut");
        editOption.add(selectOption);
        editOption.add(copyOption);
        editOption.add(pasteOption);
        editOption.add(cutOption);

        // this create the sub menu for the option "About" in the main menu bar
        timeOption = new JMenuItem("Time and Date");
        infoOption = new JMenuItem("Info");
        aboutOption.add(timeOption);
        aboutOption.add(infoOption);


        //Action listners

        //File
        newOption.addActionListener(this);
        saveOption.addActionListener(this);
        openOption.addActionListener(this);
        printOption.addActionListener(this);
        exitOption.addActionListener(this);

        //Edit
        selectOption.addActionListener(this);
        copyOption.addActionListener(this);
        pasteOption.addActionListener(this);
        cutOption.addActionListener(this);

        //Search
        searchOption.addActionListener(this);

        //About
        timeOption.addActionListener(this);
        infoOption.addActionListener(this);

        // Create and add the text area
        JTextArea textArea;
        textArea = new JTextArea();
        this.add(textArea);


        // Make the window/frame visible.
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent event) {
        // Get event
        JComponent source = (JComponent) event.getSource();
        // If user clicks Date & Time option
        if(source == timeOption){
            // Grab the current Date & Time, then format it into a string
            LocalDateTime nowDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
            String formattedDateTime = nowDateTime.format(formatter);
            // Changes the title of the window to include the Time & Date
            this.setTitle("[Scribe] Date: " + formattedDateTime);
        }else if(source == infoOption){ // If user clicks Info option
            // Create new JFrame for Popup
            popUp = new JFrame("Info");
            popUp.setTitle("Info");
            popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            popUp.setSize(400, 200);
            popUp.setLocationRelativeTo(null);
            
            // Pop Up Text
            JLabel popUpText;
            popUpText = new JLabel("Welcome to the Scribe Text Editor, built by Taylor & James");
            
            JPanel popUpPanel = new JPanel();
            popUpPanel.add(popUpText);
            popUp.add(popUpPanel);

            popUp.setVisible(true);
        }else if(source == newOption){
            //New Button clicked
        }else if(source == openOption){
            //Open Button clicked
        }else if(source == saveOption){
            //Save Button clicked
        }else if(source == printOption){
            //Print Button clicked
        }else if(source == exitOption){
            //Exit Button clicked
            System.exit(0);
        }else if(source == selectOption){
            //Select Button clicked
        }else if(source == copyOption){
            //Copy Button clicked
        }else if(source == pasteOption){
            //Paste Button clicked
        }else if(source == cutOption){
            //Cut Button clicked
        }else if(source == searchOption){
          
        }
    }
    public static void main( String[] args )
    {
        System.out.println( "Starting App..." );
        new Assign1();
    }
}
