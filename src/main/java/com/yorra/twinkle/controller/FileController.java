package com.yorra.twinkle.controller;

import com.yorra.twinkle.service.FileService;
import com.yorra.twinkle.service.UploadFileService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class FileController {
    private final UploadFileService uploadFileService;
    private final FileService fileService;

    public FileController(UploadFileService uploadFileService, FileService fileService) {
        this.uploadFileService = uploadFileService;
        this.fileService = fileService;
    }

    @GetMapping("/files")
    public List<String> getAllFile() {
        return fileService.getAllFile();
    }

    @PostMapping("file/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        uploadFileService.uploadFile(file);
    }

    @DeleteMapping("file/{id}")
    public void deleteFile(@PathVariable Long id) {
        fileService.deleteFile(id);
    }
}
