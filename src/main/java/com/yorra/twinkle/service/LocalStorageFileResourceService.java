package com.yorra.twinkle.service;

import com.yorra.twinkle.model.File;
import com.yorra.twinkle.model.enums.EFileType;
import com.yorra.twinkle.repository.FileRepository;
import lombok.Data;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Data
public class LocalStorageFileResourceService implements FileResourceService {
    private final FileRepository fileRepository;

    @Override
    @Transactional
    public File uploadFile(MultipartFile file) {
        File newFile = new File();
        if (file.getContentType().contains("video")) {
            newFile.setFileType(EFileType.VIDEO);
        } else newFile.setFileType(EFileType.IMAGE);
        newFile.setName(file.getOriginalFilename());
        newFile.setSize(file.getSize());
        String[] names = file.getOriginalFilename().split("\\.");
        newFile.setExt(names[names.length - 1]);


        try {
            newFile.setPath(downloadFile(file).getURI().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileRepository.save(newFile);
        System.out.println(newFile);
        return newFile;
    }

    @Override
    public Resource downloadFile(Long id) {
        return null;
    }

    @Override
    public Resource downloadFile(MultipartFile fileDown) {
        String[] names = fileDown.getOriginalFilename().split("\\.");
        int lengthOriginName = fileDown.getOriginalFilename().length();

        // UUID.randomUUID().toString();

        String name = fileDown.getOriginalFilename().substring(0, lengthOriginName - names[names.length - 1].length() - 1);
        String ext = names[names.length - 1];

        // hard coded
        String path = "src/main/resources/images/" + name + "." + ext;
        java.io.File file = new java.io.File(path);

        int i = 1;
        while (file.exists()) {
            path = "src/main/resources/images/" + name + "(" + i + ")." + ext;
            file = new java.io.File(path);
            i++;
        }
        try {
            byte[] bytes = fileDown.getBytes();
            Files.write(Path.of(path), bytes);
        } catch (IOException e) {
            throw new RuntimeException("error file!");
        }
        return new FileSystemResource(path);
    }
}
