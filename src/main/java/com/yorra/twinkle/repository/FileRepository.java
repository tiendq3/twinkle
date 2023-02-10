package com.yorra.twinkle.repository;

import com.yorra.twinkle.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {
    File findByName(String name);
}
