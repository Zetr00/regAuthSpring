package com.example.authreg.repo;

import com.example.authreg.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<BookModel, Long> {
    Iterable<BookModel> findByNameContainingIgnoreCase(String name);
}

