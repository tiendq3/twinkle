package com.yorra.twinkle.service;

import com.yorra.twinkle.repository.FileRepository;
import com.yorra.twinkle.service.entities.FileServiceImpl;
import com.yorra.twinkle.service.other.LocalStorageFileResourceService;
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

@SpringBootTest
public class FileServiceImplIT {
    @Autowired
    private LocalStorageFileResourceService uploadFileService;

    @Autowired
    private FileServiceImpl fileServiceImpl;
    @Autowired
    private FileRepository fileRepository;
    private MultipartFile[] files;

    @BeforeEach
    public void init() {
        files = new MultipartFile[]{
                new MockMultipartFile("test.jpg", "test.jpg", "image/jpg", "test".getBytes())
        };
    }

    @Test
    public void uploadFileTest() {
        uploadFileService.uploadFile(files);
        var oFileDB = fileRepository.findByName("test.jpg");
        var fileDB = oFileDB.get();
        java.io.File fileServer = new java.io.File(fileDB.getPath());

        Assertions.assertTrue(fileServer.exists());
        try {
            byte[] fileServerBytes = Files.readAllBytes(Paths.get(fileDB.getPath()));
            Assertions.assertArrayEquals(fileServerBytes, "test".getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileServiceImpl.deleteFile(fileDB.getId());
    }

}
