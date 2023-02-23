package com.yorra.twinkle.service.other;

import com.yorra.twinkle.model.entities.File;
import com.yorra.twinkle.model.enums.EFileType;
import com.yorra.twinkle.repository.FileRepository;
import com.yorra.twinkle.service.FileResourceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LocalStorageFileResourceService implements FileResourceService {
    private final FileRepository fileRepository;

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
            String filePath = "src/main/resources/images/" + randomName;
            newFile.setPath(filePath);
            fileRepository.save(newFile);
            fileList.add(newFile);

            //download file
            try {
                file.transferTo(Paths.get(filePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return fileList;
    }
}
