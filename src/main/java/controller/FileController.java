package controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.File;

@CrossOrigin("*")
@Controller
@RequestMapping("v1/")
public class FileController {

    @PostMapping("upload")
    public void importFile(@NotNull @RequestParam File file) {
        //todo
        System.out.println(file.getAbsolutePath());
    }

    @GetMapping("get")
    public String getFile() {
        //todo
        System.out.println("AAAAAAAAAAAAAAA");
        return "AAAAAAAAAAAAAAAA";
    }
}
