package Assignment1JamesTaylor;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Assign1
 */
public class Assign1Test{

    @Test
    public void searchFunctionTest()throws Exception{
        File testFile1 = new File("test1.cpp");
        if(testFile1.createNewFile()){
            try {
                Files.write(Paths.get("test1.cpp"), "test123\n".getBytes(), StandardOpenOption.APPEND);
            }catch (IOException e) {
                assertTrue(false);
            }
            Assign1 assign1 = new Assign1(true);
            assign1.openFile(testFile1);
            //searchInFile returns the position of the string in the file
            assertEquals(0, assign1.searchInFile("test"));
            testFile1.delete();
        }else{
            assertTrue(false);
        } 
    }

    @Test
    public void openFunctionTest() throws Exception{
        File testFile2 = new File("test2.cpp");
        if(testFile2.createNewFile()){
            //if the file opens, openFile returns true
            assertTrue(new Assign1(true).openFile(testFile2));
            testFile2.delete();
        }else{
            assertTrue(false);
        }
    }

    @Test
    public void saveFunctionTest() throws Exception{
        File testFile3 = new File("test3.cpp");
        new Assign1(true).saveFile(testFile3);
        //if the file was created, it should be able to be deleted
        assertTrue(testFile3.delete());
    }
}
