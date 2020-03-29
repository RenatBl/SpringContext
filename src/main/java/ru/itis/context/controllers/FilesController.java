package ru.itis.context.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.context.services.FilesStorageService;
import ru.itis.context.services.UsersService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class FilesController {
    @Autowired
    private FilesStorageService service;

    @Autowired
    private UsersService usersService;

    @GetMapping("/files")
    public String getFilesPage() {
        return "files";
    }

    @PostMapping("/files")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, HttpSession session) {
        String filePath = service.save(file);
        return ResponseEntity
                .ok()
                .body(filePath
                        + " " + session.getAttribute("username")
                        + " " + usersService.getUserById((Long) session.getAttribute("user")).getEmail());
    }

    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName,
                        HttpServletResponse response) {
        service.getFile(fileName, response);
    }

}
