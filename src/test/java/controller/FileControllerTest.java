package controller;

import org.junit.Test;
import run.Run;

import java.io.File;

import static org.junit.Assert.*;

public class FileControllerTest {

    @Test
    public void importFile() {
        //todo
        FileController fileController = new FileController();
        //String path = Run.class.getResource("input.csv").getPath();
        String path = "/Users/michalkleszczewski/Documents/Development/Projects/JAVA/newspoint-task2/src/main/resources/input.csv";
        //System.out.println(path);
        fileController.importFile(new File(path));
        //System.out.println(path);
    }
}