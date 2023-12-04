package com.example.authreg.repo;

import com.example.authreg.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserModel, Long>  {
    UserModel findByName(String name);
}
