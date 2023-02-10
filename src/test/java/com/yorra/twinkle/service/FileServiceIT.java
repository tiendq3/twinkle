package com.yorra.twinkle.service;

import com.yorra.twinkle.model.File;
import com.yorra.twinkle.repository.FileRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@SpringBootTest
public class FileServiceIT {
    @Autowired
    private UploadFileService uploadFileService;

    @Autowired
    private FileService fileService;
    @Autowired
    private FileRepository fileRepository;
    private MultipartFile file;

    @BeforeEach
    public void init() {
        file = new MockMultipartFile("test.jpg", "test.jpg", "image/jpg", "test".getBytes());
    }

    @Test
    public void uploadFileTest() {
        uploadFileService.uploadFile(file);
        var ofileDB = fileRepository.findByName("test.jpg");
        var fileDB = ofileDB.get();
        java.io.File fileServer = new java.io.File(fileDB.getPath());

        Assertions.assertTrue(fileServer.exists());
        try {
            byte[] fileServerBytes = Files.readAllBytes(Paths.get(fileDB.getPath()));
            Assertions.assertTrue(Arrays.equals(fileServerBytes, "test".getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileService.deleteFile(fileDB.getId());
    }

}
