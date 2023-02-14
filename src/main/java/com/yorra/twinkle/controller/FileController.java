package com.yorra.twinkle.controller;

import com.yorra.twinkle.model.File;
import com.yorra.twinkle.service.FileService;
import com.yorra.twinkle.service.UploadFileService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Data
@RequestMapping("/api/v1")
public class FileController {
    private final UploadFileService uploadFileService;
    private final FileService fileService;

    // path api /api/v1/management/files
    // Paging
    // Page<File>
    @GetMapping("/management/files")
    public List<String> getAllFile() {
        // log.info("GET all file request")
        return fileService.getAllFile();
    }

    // return File
    @PostMapping("/management/files/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        uploadFileService.uploadFile(file);
    }

    @DeleteMapping("/management/files/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFile(@PathVariable Long id) {
        fileService.deleteFile(id);
    }

    // GetById
    @GetMapping("/files/{id}")
    public File getFileById(@PathVariable Long id) {
        return new File();
    }
}
