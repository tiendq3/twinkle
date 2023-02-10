package com.yorra.twinkle.service;

import com.yorra.twinkle.exception.NotFoundFileException;
import com.yorra.twinkle.model.File;
import com.yorra.twinkle.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
// LOMBOK
public class FileService {
    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<String> getAllFile() {
        List<File> files = fileRepository.findAll();
        return files.stream().map(File::getPath).collect(Collectors.toList());
    }

    @Transactional
    public void deleteFile(Long id) {
        Optional<File> optionalFile = fileRepository.findById(id);
        if (optionalFile.isEmpty()) throw new NotFoundFileException("Not found file by " + id);
        String path = optionalFile.get().getPath();
        fileRepository.delete(optionalFile.get());
        java.io.File file = new java.io.File(path);
        file.delete();
    }
}
