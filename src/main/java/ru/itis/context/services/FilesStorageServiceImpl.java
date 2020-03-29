package ru.itis.context.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.context.models.FileInfo;
import ru.itis.context.repo.FileRepo;
import ru.itis.context.utils.FileUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Component
public class FilesStorageServiceImpl implements FilesStorageService {
    @Autowired
    private FileUtil util;

    @Autowired
    private FileRepo repo;

    @Override
    public String save(MultipartFile multipartFile) {
        FileInfo fileInfo = util.convertFromMultipart(multipartFile);
        repo.save(fileInfo);
        util.copyToStorage(multipartFile, fileInfo.getStorageName());
        return fileInfo.getStorageName();
    }

    @Override
    public void getFile(String fileName, HttpServletResponse response) {
        try {
            Optional<FileInfo> fileCandidate = repo.findByName(fileName);
            if (fileCandidate.isPresent()) {
                FileInfo file = fileCandidate.get();
                response.setContentType(file.getType());
                InputStream inputStream = new FileInputStream(new java.io.File(file.getUrl()));
                org.apache.commons.io.IOUtils.copy(inputStream, response.getOutputStream());
                response.flushBuffer();
            } else {
                throw new IllegalArgumentException("File not found");
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


}
