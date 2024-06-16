package com.anthony.biblioteca_virtual.file;

import com.anthony.biblioteca_virtual.book.Book;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
@RequiredArgsConstructor
public class FinalStorageService {

    @Value("${application.file.upload.photos-path}")
    private String fileUploadPath;

    public String saveFile(@NonNull MultipartFile sourceFile,
                           @NonNull Integer userId) {
        final String fileUploadSubPath = "users" + File.separator + userId;
        return uploadFile(sourceFile, fileUploadSubPath);
    }

    private String uploadFile(@NonNull MultipartFile sourceFile, @NonNull String fileUploadSubPath) {
        final String finalUploadPath = fileUploadPath + File.separator + fileUploadSubPath;
        File targetFile = new File(finalUploadPath);
        if(!targetFile.exists()) {
            boolean folderCreated = targetFile.mkdirs();
            if(!folderCreated) {
                log.warn("Failed to create folder {}", targetFile.getAbsolutePath());
                return null;
            }
        }
        final String fileExtension = getFileExtension(sourceFile.getOriginalFilename());
        String targetFilePath = finalUploadPath + File.separator + System.currentTimeMillis() + "." + fileExtension;
        Path tagetPath = Paths.get(targetFilePath);
        try {
            Files.write(tagetPath, sourceFile.getBytes());
            log.info("Uploaded file {} to {}", sourceFile.getOriginalFilename(), targetFilePath);
            return targetFilePath;
        } catch (IOException e) {
            log.error("Failed to upload file {}", sourceFile.getOriginalFilename(), e);
        }
        return null;
    }

    private String getFileExtension(String fileName) {
        if(fileName == null || fileName.isEmpty()) {
            return null;
        }
        final int lastIndexOfPoint = fileName.lastIndexOf('.');
        if(lastIndexOfPoint == -1) {
            return null;
        }
        return fileName.substring(lastIndexOfPoint + 1).toLowerCase();
    }
}
