package Assignment1JamesTaylor;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
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
    private  JTextArea textArea;
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
        saveOption = new JMenuItem("Save As");
        printOption = new JMenuItem("Print");
        exitOption = new JMenuItem("Exit");

        fileOption.add(newOption);
        fileOption.add(openOption);
        fileOption.add(saveOption);
        fileOption.add(printOption);
        fileOption.add(exitOption);

        // this create the sub menu for the option "Edit" in the main menu bar
        selectOption = new JMenuItem("Select All");
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
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        this.add(scrollPane);


        // Make the window/frame visible.
        this.setVisible(true);
    }
    void clearScreen(){
        textArea.setText("");
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
            clearScreen();//change me
        }else if(source == openOption){
            //Open Button clicked
            clearScreen();
            JFileChooser openFileChooser = new JFileChooser("./");
            int result = openFileChooser.showOpenDialog(this);
            if(result == JFileChooser.APPROVE_OPTION){
                File selectedFile = openFileChooser.getSelectedFile();
                try{
                    FileReader fReader = new FileReader(selectedFile);
                    BufferedReader bReader = new BufferedReader(fReader);
                    String str = "";
                    while((str = bReader.readLine() ) != null){
                        textArea.append(str);
                        textArea.append("\n");
                    }
                    bReader.close();
                
                }catch(FileNotFoundException e){
                    System.out.println("Error: File not found.");
                }catch(IOException e){
                    e.printStackTrace();
                }

            }else{
                System.out.println("Error: Could not open file.");
            }
        }else if(source == saveOption){
            //Save Button clicked
            JFileChooser saveFileChooser = new JFileChooser("./");
            int result = saveFileChooser.showSaveDialog(this);
            if(result == JFileChooser.APPROVE_OPTION){
                File newFile = saveFileChooser.getSelectedFile();
                try{
                    FileWriter fWriter = new FileWriter(newFile);
                    BufferedWriter bWriter = new BufferedWriter(fWriter);
                    String fileContents = textArea.getText().replaceAll("\n", System.lineSeparator());
                    bWriter.write(fileContents, 0, fileContents.length());
                    bWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if(source == printOption){
            //Print Button clicked
        }else if(source == exitOption){
            //Exit Button clicked
            System.exit(0);
        }else if(source == selectOption){
            //Select Button clicked
            textArea.selectAll();
        }else if(source == copyOption){
            //Copy Button clicked
            String text  = textArea.getSelectedText();
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection data = new StringSelection(text);
            clip.setContents(data, null);

        }else if(source == pasteOption){
            //Paste Button clicked
            try {
                String text = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
                textArea.append(text);
            } catch (Exception e) {
                e.printStackTrace();
            }

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
