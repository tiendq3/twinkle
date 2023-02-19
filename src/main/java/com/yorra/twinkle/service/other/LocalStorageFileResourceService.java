package com.yorra.twinkle.service.other;

import com.yorra.twinkle.model.entities.File;
import com.yorra.twinkle.model.enums.EFileType;
import com.yorra.twinkle.repository.FileRepository;
import com.yorra.twinkle.service.FileResourceService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
public class LocalStorageFileResourceService implements FileResourceService {
    private final FileRepository fileRepository;

    //    private final List<FileResourceService> uploadService;
    @Transactional
    public List<File> uploadFile(MultipartFile[] files) {
        if (files == null || files.length == 0) throw new RuntimeException("file mustn't null");
        List<File> fileList = new ArrayList<>();
        for (MultipartFile file : files) {
            File newFile = new File();

            // set file type
            if (file == null || file.getContentType() == null) {
                throw new RuntimeException("file mustn't null!");
            } else if (file.getContentType().contains("video")) {
                newFile.setFileType(EFileType.VIDEO);
            } else if (file.getContentType().contains("image")) {
                newFile.setFileType(EFileType.IMAGE);
            } else
                throw new RuntimeException("The file must be an image or a video!");

            //set file ext
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
            newFile.setExt(fileExtension);

            String randomName = UUID.randomUUID() + "." + fileExtension;
            newFile.setName(randomName);
            newFile.setSize(file.getSize());


            //hard code
            newFile.setPath("src/main/resources/images/" + randomName);
            fileRepository.save(newFile);
            fileList.add(newFile);
            try {
                downloadFile(newFile.getId(), file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return fileList;
    }

    public File downloadFile(Long id, byte[] bytes) {
        Optional<File> optionalFile = fileRepository.findById(id);
        if (optionalFile.isEmpty()) throw new RuntimeException("upload/download error");
        File file = optionalFile.get();

        String path = file.getPath();
        java.io.File fileDown = new java.io.File(path);

        try {
            Files.write(Path.of(path), bytes);
        } catch (IOException e) {
            throw new RuntimeException("error file!");
        }
        return file;
    }
}
