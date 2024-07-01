package com.ladeologun.movieflixapi.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class FileServiceImpl implements FileService{
    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        String filePath = path + File.pathSeparator + fileName;
        File fileObj = new File(filePath);
        if (!fileObj.exists()){
            fileObj.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

    @Override
    public InputStream getResourceFile(String path, String fileName) throws FileNotFoundException {
        String filePath = path + File.pathSeparator + fileName;
        return new FileInputStream(filePath);
    }
}
