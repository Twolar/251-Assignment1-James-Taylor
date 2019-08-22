package Assignment1JamesTaylor;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.Color;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.commons.io.FilenameUtils;

import javax.swing.*;

import java.awt.event.*;

/**
 * Hello world!
 *
 */
public class Assign1 extends JFrame implements ActionListener, KeyListener{
    private JMenuBar menuBar; 
    private JMenu fileOption, editOption,  aboutOption, searchOption;
    private JMenuItem exportPdfOption, newOption, saveOption, openOption, printOption, exitOption, selectOption, copyOption, pasteOption, cutOption, timeOption, infoOption;
    private JFrame popUp;
    private JPanel searchDropDownPanel;
    private JTextField searchInputField;
    private JButton searchGoButton;
    private  JTextPane textPane;
    private String prevSearchQueryText;
    private String searchQueryText;
    
    //Attributes
    private SimpleAttributeSet blueText;
    private SimpleAttributeSet orangeText;
    private SimpleAttributeSet highlightedText;
    private SimpleAttributeSet clearBackgroundColor;
    private StyledDocument document;
    
    public Assign1(){
        // create the frame
        super("[Scribe]");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        // This creates main menu bar options 
        fileOption = new JMenu("File");
        editOption = new JMenu("Edit");
        searchOption = new JMenu("Search");
        aboutOption = new JMenu("About");

	    // Create main menuBar and add options to it
	    menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        menuBar.add(fileOption);
        menuBar.add(editOption);
        menuBar.add(aboutOption);
        menuBar.add(searchOption);
	    
	    // Create search dropdown panel and add to menuBar search
        searchDropDownPanel = new JPanel();
        searchInputField = new JTextField(30);
        searchGoButton = new JButton("Go");
        searchDropDownPanel.add(searchInputField);
        searchDropDownPanel.add(searchGoButton);
        searchOption.add(searchDropDownPanel);
        
        // this create the sub menu for the option "File" in the main menu bar
        newOption = new JMenuItem("New");
        openOption = new JMenuItem("Open");
        saveOption = new JMenuItem("Save As");
        exportPdfOption = new JMenuItem("Export As PDF");
        printOption = new JMenuItem("Print");
        exitOption = new JMenuItem("Exit");

        fileOption.add(newOption);
        fileOption.add(openOption);
        fileOption.add(saveOption);
        fileOption.add(exportPdfOption);
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
        exportPdfOption.addActionListener(this);
        printOption.addActionListener(this);
        exitOption.addActionListener(this);

        //Edit
        selectOption.addActionListener(this);
        copyOption.addActionListener(this);
        pasteOption.addActionListener(this);
        cutOption.addActionListener(this);

        //Search
        searchGoButton.addActionListener(this);

        //About
        timeOption.addActionListener(this);
        infoOption.addActionListener(this);

        // Create and add the text area
        textPane = new JTextPane();
        textPane.addKeyListener(this);
        JScrollPane scrollPane = new JScrollPane(textPane);
        this.add(scrollPane);
        document = textPane.getStyledDocument();
        
        //Init attributes/styles here for text color
        blueText = new SimpleAttributeSet();
        StyleConstants.setForeground(blueText, Color.BLUE);
        orangeText = new SimpleAttributeSet();
        StyleConstants.setForeground(orangeText, Color.ORANGE);
        highlightedText = new SimpleAttributeSet();
        StyleConstants.setBackground(highlightedText, Color.cyan);
        clearBackgroundColor = new SimpleAttributeSet();
        StyleConstants.setBackground(clearBackgroundColor, new Color(0, 0, 0, 0));

        // Make the window/frame visible.
        this.setVisible(true);
    }
    void clearScreen(){
        textPane.setText("");
    }

    void changeKeywordColor(String str, String[] array, Color color, String notAllowed){
        String keyword = "";
        int pos = 0;
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr, color);
        for(int x = 0; x<array.length; x++){
            keyword = array[x];
            
            for (int i = -1; (i = str.indexOf(keyword, i)) != -1; i++) {
                if((i <= 1)||((i+keyword.length()) >= str.length())){
                    pos = ((document.getLength()-str.length())+i)-1;
                    document.setCharacterAttributes(pos, keyword.length(), attr, false);
                }else if(((notAllowed.indexOf(str.charAt(i-1))) == -1) && ((notAllowed.indexOf(str.charAt(i+keyword.length()))) == -1)){
                    pos = ((document.getLength()-str.length())+i)-1;
                    document.setCharacterAttributes(pos, keyword.length(), attr, false);
                }
            } 
        }

    }
    void setLineColour(String str, String[] array, Color color){
        String keyword = "";
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr, color);
        int i = -1;
        int j = -1;
        for(int x = 0; x<array.length; x++){
            keyword = array[x];
            if(str.indexOf(keyword, i) != -1){
                document.setCharacterAttributes(document.getLength()-str.length()+i, str.length(), attr, false);
            }
        }
    }

    void insertWithColor(String str){
        String[] blueKeywords = {"void", "public", "private","int", "this", "true", "false","float", "String",
                                 "extends", "implements", "import", "package"};
        String[] numbers = {"0", "1","2","3","4","5","6","7","8","9"};
        String[] extra = {"while", "for", "new"};
        String[] characters = {"(", ")", "{", "}", "[", "]"}; 
        String[] fullLineChars = {"@", "//"};
        String numAndLetters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890\" ";
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\"";


        changeKeywordColor(str, blueKeywords, new Color(0,0,139), numAndLetters);
        changeKeywordColor(str, numbers, new Color(0,128,0), letters);
        changeKeywordColor(str, extra, new Color(148,0,211), numAndLetters);
        changeKeywordColor(str, characters, new Color(148,0,211), "\"");
        setLineColour(str, fullLineChars, new Color(50,150,50));

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
            new Assign1();
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
                        document.insertString(document.getLength(), str+"\n", null);
                        insertWithColor(str);
                    }
                    bReader.close();
                
                }catch(FileNotFoundException e){
                    System.out.println("Error: File not found.");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch( BadLocationException e){
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
                    String fileContents = textPane.getText().replaceAll("\n", System.lineSeparator());
                    bWriter.write(fileContents, 0, fileContents.length());
                    bWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if(source == exportPdfOption){
            JFileChooser saveFileChooser = new JFileChooser("./");
            int result = saveFileChooser.showSaveDialog(this);
            if(result == JFileChooser.APPROVE_OPTION){
                File newFile = saveFileChooser.getSelectedFile();
                
                // .pdf extension manager
                if (FilenameUtils.getExtension(newFile.getName()).equalsIgnoreCase("pdf")) {
                    // Leave file name how it is
                } else {
                     // If extension != pdf, then replace with .pdf. Or if there is no extension, add .pdf
                    newFile = new File(newFile.getParentFile(), FilenameUtils.getBaseName(newFile.getName())+".pdf"); 
                }


                // create output stream to newfile based off whats typed or selected as the save file name in saveDialog
                OutputStream newsFileOutputStream = null;
                try {
                    newsFileOutputStream = new FileOutputStream(newFile);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                // Write what ever is in our textPane into pdf
                Document newPdf = new Document();
                try {
                    PdfWriter writer = PdfWriter.getInstance(newPdf,  newsFileOutputStream);
                    newPdf.open();
                    newPdf.add(new Paragraph(textPane.getText()));
                    newPdf.close();
                    writer.close();
                }catch (DocumentException e1){
                    e1.printStackTrace();
                }
            }
        }else if(source == printOption){
            //Print Button clicked
        }else if(source == exitOption){
            //Exit Button clicked
            System.exit(0);
        }else if(source == selectOption){
            //Select Button clicked
            textPane.selectAll();
            //StyleConstants.setForeground(blueText, Color.BLUE);
            document.setCharacterAttributes(0, document.getLength(), blueText, true);
            
        }else if(source == copyOption){
            //Copy Button clicked
            String text  = textPane.getSelectedText();
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection data = new StringSelection(text);
            clip.setContents(data, null);

        }else if(source == pasteOption){
            //Paste Button clicked
            try {
                String text = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
                document.insertString(document.getLength(), text, null);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else if(source == cutOption){
            //Cut Button clicked
            //Copy and Delete after...
            String text  = textPane.getSelectedText();
            textPane.setText(textPane.getText().replace(textPane.getSelectedText(),""));
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection data = new StringSelection(text);
            clip.setContents(data, null);

        }else if(source.equals(searchGoButton)){
            // Read what ever is in searchTextField into a string if Go is presse
            prevSearchQueryText = searchQueryText;
            if(prevSearchQueryText != null){
                document.setCharacterAttributes(0, document.getLength(), clearBackgroundColor, false);
            }
            searchQueryText = searchInputField.getText();

             
            System.out.println("Search Query: " + searchQueryText); // DEBUG
            try {
                for (int i = -1; (i = document.getText(0, document.getLength()).indexOf(searchQueryText,i + 1)) != -1; i++) {

                    document.setCharacterAttributes(i, searchQueryText.length(), highlightedText, false);
                }
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

        }
    }
    public static void main( String[] args )
    {
        System.out.println( "Starting App..." );
        new Assign1();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        /*
        System.out.print(e.getKeyChar());
        if((e.getKeyChar() == '(')||(e.getKeyChar() == ')')){
            //set ( and ) to orange
            changeCharacterColor(orangeText);  
        }else{
            changeCharacterColor(blueText);
        }
        */
        
    }

    public void changeCharacterColor(SimpleAttributeSet attr){
        document.setCharacterAttributes(document.getLength()-1, 1, attr, false); 

    }

    
}
