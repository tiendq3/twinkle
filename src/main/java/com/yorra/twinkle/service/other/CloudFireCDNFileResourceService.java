package com.yorra.twinkle.service.other;

import com.yorra.twinkle.model.entities.File;
import com.yorra.twinkle.service.FileResourceService;
import lombok.Data;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Data
public class CloudFireCDNFileResourceService implements FileResourceService {

    @Override
    public List<File> uploadFile(MultipartFile[] inputFiles) {
        return null;
    }
}
