package application.controller;

import application.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@CrossOrigin("*")
@Controller
@RequestMapping("v1/")
public class FileController {
    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("upload")
    public void importFile(@RequestParam("file") MultipartFile file) throws IOException {
        fileService.dataProcessing(file);
    }
}
