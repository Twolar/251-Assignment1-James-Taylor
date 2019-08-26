package Assignment1JamesTaylor;

import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for Assign1
 */
public class Assign1Test{

    @Test
    public void searchFunctionTest(){
        //add test here
        
        
    }

    @Test
    public void openFunctionTest(){
        //add test here
        
    }

    @Test
    public void saveFunctionTest() throws Exception{
        //add test here
        File testFile = new File("test.cpp");
        new Assign1(true).saveFile(testFile);
        //if the file was created, it should be able to be deleted
        assertTrue(testFile.delete());
        
        
    }
}
