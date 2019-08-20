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
        super("[Scribe]");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
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
