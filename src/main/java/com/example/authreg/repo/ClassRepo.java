package com.example.authreg.repo;

import com.example.authreg.model.ClassModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepo extends JpaRepository<ClassModel, Long> {
    ClassModel findByName(String name);
}
