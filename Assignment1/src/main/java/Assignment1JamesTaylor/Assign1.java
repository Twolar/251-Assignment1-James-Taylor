package Assignment1JamesTaylor;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

//import java.awt.print.*;
//import javax.print.PrintService;

import org.apache.commons.io.FilenameUtils;
//import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

import javax.swing.*;
import java.awt.event.*;



/**
 * Hello world!
 *
 */
public class Assign1 extends JFrame implements ActionListener{
    private JMenuBar menuBar; 
    private JMenu fileOption, editOption,  aboutOption, searchOption;
    private JMenuItem exportPdfOption, newOption, saveOption, openOption, printOption, exitOption, selectOption, copyOption, pasteOption, cutOption, timeOption, infoOption;
    private JFrame popUp;
    private JPanel searchDropDownPanel;
    private JTextField searchInputField;
    private JButton searchButton;
    private  RSyntaxTextArea  textArea;
    private String searchQueryText;
    private int findPosition = -1;
    
    public Assign1(boolean isWindow){
        // create the frame
        super("[Scribe]");

        // Exiting original window terminates program, exiting any new Windows only terminates those window instances.
        if(isWindow) {
            this.dispose();
        } else {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
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
        searchButton = new JButton("Find");
        searchDropDownPanel.add(searchInputField);
        searchDropDownPanel.add(searchButton);
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
        searchButton.addActionListener(this);

        //About
        timeOption.addActionListener(this);
        infoOption.addActionListener(this);

        // Create and add the text area
        textArea = new RSyntaxTextArea();

        JScrollPane scrollPane = new JScrollPane(textArea);
        this.add(scrollPane);
        // Make the window/frame visible.
        this.setVisible(true);
    }
    void clearScreen(){
        textArea.setText("");
    }
    
    public String getFileType(String ex){
        switch(ex){
            case ".c":
                return SyntaxConstants.SYNTAX_STYLE_C;
            case ".cpp":
                return SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS;
            case ".py":
                return SyntaxConstants.SYNTAX_STYLE_PYTHON;
            case ".css":
                return SyntaxConstants.SYNTAX_STYLE_CSS;
            case ".csharp":
                return SyntaxConstants.SYNTAX_STYLE_CSHARP;
            case ".java":
                return SyntaxConstants.SYNTAX_STYLE_JAVA;
            case ".html":
                return SyntaxConstants.SYNTAX_STYLE_HTML;
            case ".xml":
                return SyntaxConstants.SYNTAX_STYLE_XML;
            case ".yml":
                return SyntaxConstants.SYNTAX_STYLE_YAML;
            case ".js":
                return SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT;
            case ".php":
                return SyntaxConstants.SYNTAX_STYLE_PHP;
            case ".json":
                return SyntaxConstants.SYNTAX_STYLE_JSON;
            case ".sql":
                return SyntaxConstants.SYNTAX_STYLE_SQL;
            case ".bat":
                return SyntaxConstants.SYNTAX_STYLE_WINDOWS_BATCH;
            default:
                return SyntaxConstants.SYNTAX_STYLE_NONE;
        }
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
            new Assign1(true);
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
                    String extension = "";
                    int index = -1;
                    index = selectedFile.getName().indexOf(".");
                    extension =  selectedFile.getName().substring(index);
                    textArea.setSyntaxEditingStyle(getFileType(extension));
                    while((str = bReader.readLine() ) != null){
                        textArea.append(str+"\n");
                    }
                    bReader.close();
                
                }catch(FileNotFoundException e){
                    System.out.println("Error: File not found.");
                } catch (IOException e) {
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
                String extension = "";
                int index = -1;
                index = newFile.getName().indexOf(".");
                extension =  newFile.getName().substring(index);
                textArea.setSyntaxEditingStyle(getFileType(extension));
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
                    newPdf.add(new Paragraph(textArea.getText()));
                    newPdf.close();
                    writer.close();
                    JOptionPane.showMessageDialog(null, "Document successfully converted and exported as PDF.");
                }catch (DocumentException e1){
                    JOptionPane.showMessageDialog(null, "Error in PDF conversion & export.");
                    e1.printStackTrace();
                }
            }
        }else if(source == printOption){
            try {
                boolean printDone = textArea.print();
                if (printDone) {
                    JOptionPane.showMessageDialog(null, "Printing is done");
                } else {
                    JOptionPane.showMessageDialog(null, "Error while printing");
                }
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Error while printing");
                e2.printStackTrace();
            }
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
                textArea.insert(text, textArea.getCaretPosition());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(source == cutOption){
            //Cut Button clicked
            //Copy and Delete after...
            String text  = textArea.getSelectedText();
            textArea.setText(textArea.getText().replace(textArea.getSelectedText(),""));
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection data = new StringSelection(text);
            clip.setContents(data, null);
        }else if(source.equals(searchButton)){
            // Read what ever is in searchTextField into a string if Go is presse
            searchQueryText = searchInputField.getText();
            findPosition = textArea.getText().indexOf(searchQueryText, findPosition + 1);
            if(searchQueryText != null){
                if(findPosition > -1){
                    textArea.setSelectionStart(findPosition);
                    textArea.setSelectionEnd(findPosition+ searchQueryText.length());
                }else{
                    findPosition = textArea.getText().indexOf(searchQueryText, findPosition + 1);
                    textArea.setSelectionStart(findPosition);
                    textArea.setSelectionEnd(findPosition+ searchQueryText.length());
                }
            }
            //System.out.println("Search Query: " + searchQueryText); // DEBUG

        }
    }
    public static void main( String[] args )
    {
        System.out.println( "Starting App..." );
        new Assign1(false);
    }   
}
