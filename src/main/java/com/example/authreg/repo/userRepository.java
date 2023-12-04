package com.example.authreg.repo;


import com.example.authreg.model.modelUser;
import org.springframework.data.repository.CrudRepository;

public interface userRepository extends CrudRepository<modelUser,Long> {
    modelUser findByUsername(String username);
}
