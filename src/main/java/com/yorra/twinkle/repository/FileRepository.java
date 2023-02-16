package com.yorra.twinkle.repository;

import com.yorra.twinkle.model.entities.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    Optional<File> findByName(String name);

    Page<File> findAll(Pageable pageable);
}
