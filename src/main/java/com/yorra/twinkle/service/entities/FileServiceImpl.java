package com.yorra.twinkle.service.entities;

import com.yorra.twinkle.exception.NotFoundException;
import com.yorra.twinkle.model.entities.File;
import com.yorra.twinkle.repository.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FileServiceImpl {
    private final FileRepository fileRepository;

    public Page<File> getAllFilePaging(int pageNumber, int pageSize, String[] properties, Sort.Direction sort) {
        PageRequest pageable;
        if (sort.equals(Sort.Direction.DESC)) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, properties);
        } else
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, properties);
        return fileRepository.findAll(pageable);
    }

    public File getFileById(Long id) {
        Optional<File> optionalFile = fileRepository.findById(id);
        if (optionalFile.isEmpty()) throw new NotFoundException("Not found file by " + id);
        return optionalFile.get();
    }

    @Transactional
    public void deleteFile(Long id) {
        Optional<File> optionalFile = fileRepository.findById(id);
        if (optionalFile.isEmpty()) throw new NotFoundException("Not found file by " + id);
        String path = optionalFile.get().getPath();
        fileRepository.delete(optionalFile.get());
        java.io.File file = new java.io.File(path);
        file.delete();
    }

}
