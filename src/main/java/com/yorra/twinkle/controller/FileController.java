package com.yorra.twinkle.controller;

import com.yorra.twinkle.model.entities.File;
import com.yorra.twinkle.service.entities.FileService;
import com.yorra.twinkle.service.other.LocalStorageFileResourceService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Data
@RequestMapping("/api/v1")
public class FileController {
    private final LocalStorageFileResourceService uploadFileService;
    private final FileService fileService;

    @GetMapping("/management/files")
    public ResponseEntity<Page<File>> getAllFile(@RequestParam(defaultValue = "0") int pageNumber,
                                                 @RequestParam(defaultValue = "10") int pageSize,
                                                 @RequestParam(defaultValue = "name") String[] properties,
                                                 @RequestParam(defaultValue = "ASC") Sort.Direction sort) {
//         log.info("GET all file request")
        Page<File> filePaging = fileService.getAllFilePaging(pageNumber, pageSize, properties, sort);
        return ResponseEntity.status(HttpStatus.OK).body(filePaging);
    }


    @GetMapping("/files/{id}")
    public ResponseEntity<File> getFileById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(fileService.getFileById(id));
    }

    // return File
    @PostMapping("/management/files/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public List<File> uploadFile(@RequestParam("files") MultipartFile[] files) {
        System.out.println(files[0].getContentType());
        return uploadFileService.uploadFile(files);
    }

    @DeleteMapping("/management/files/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFile(@PathVariable Long id) {
        fileService.deleteFile(id);
    }


}
