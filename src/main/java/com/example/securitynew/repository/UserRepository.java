package com.example.securitynew.repository;

import com.example.securitynew.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "FROM User u where u.email =:email", nativeQuery = true)
    Optional<User> findByEmail(String email);
}
