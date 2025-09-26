package com.example.GEOhoi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.GEOhoi.Model.user;

@Repository
public interface UserRepo extends JpaRepository<user,Long> {
    Boolean findByProviderId(String providerId);
}
