package com.yorra.twinkle.service;

import com.yorra.twinkle.model.File;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CloudFireCDNFileResourceService implements FileResourceService {
    @Override
    public File uploadFile(MultipartFile inputFile) {
        return null;
    }

    @Override
    public Resource downloadFile(Long id) {
        return null;
    }

    @Override
    public Resource downloadFile(MultipartFile fileDown) {
        return null;
    }
}
