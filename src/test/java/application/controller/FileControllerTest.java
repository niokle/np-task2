package application.controller;

import application.service.FileService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileControllerTest {
    //todo
    @Autowired
    FileService fileService;

    @Test
    public void importFile() throws IOException {
        //given
        FileController fileController = new FileController(fileService);

        String path = "/Users/michalkleszczewski/Documents/Development/Projects/JAVA/newspoint-task2/src/main/resources/input.csv";
        File file = new File(path);
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                file.getName(), "text/plain", input);

        //when
        fileController.importFile(multipartFile);

        //then

    }
}