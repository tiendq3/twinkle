package com.yorra.twinkle.service;

import com.yorra.twinkle.model.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public class LocalStorageFileResourceService implements FileResourceService{
    @Override
    public File uploadFile(MultipartFile inputFile) {
        return null;
    }

    @Override
    public Resource downloadFile(Long id) {
        return null;
    }
}
