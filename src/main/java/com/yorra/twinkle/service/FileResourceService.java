package com.yorra.twinkle.service;

import com.yorra.twinkle.model.entities.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 */
public interface FileResourceService {
    List<File> uploadFile(MultipartFile[] inputFiles);
}
