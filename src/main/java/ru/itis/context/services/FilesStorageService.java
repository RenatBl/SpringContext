package ru.itis.context.services;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FilesStorageService {
    String save(MultipartFile multipartFile);
    void getFile(String fileName, HttpServletResponse response);
}
