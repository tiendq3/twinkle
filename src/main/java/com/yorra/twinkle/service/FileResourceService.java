package com.yorra.twinkle.service;

import com.yorra.twinkle.model.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 */
public interface FileResourceService {
    File uploadFile(MultipartFile inputFile);

    Resource downloadFile(Long id);

    Resource downloadFile(MultipartFile fileDown);
}
