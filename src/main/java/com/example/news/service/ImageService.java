package com.example.news.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
@Component
public class ImageService {

    public String filePath(String folder, String ext) {
        LocalDate localDate = LocalDate.now();
        String path = localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        File file = new File(folder + "/" + path);
        if (!file.exists()) {
            file.mkdirs();
        }

        String uuid = UUID.randomUUID().toString();
        return file.getPath() + "\\" + uuid + ext;
    }

    public String saveFile(MultipartFile image) {
        String ext = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
        try {
            String filePath;
            Files.copy(image.getInputStream(), Path.of(filePath = filePath("upload", ext)));
            return filePath;
        } catch (IOException e) {
            return null;
        }
    }
    public byte[] getImage(String imageUrl){
        byte[] file = new byte[0];
        try {
            file = new FileInputStream(imageUrl).readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
}
