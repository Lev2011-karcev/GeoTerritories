package com.example.GEOhoi.Repository;

import com.example.GEOhoi.Model.RunPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RunPointRepo extends JpaRepository<RunPoint, Long> {
    List<RunPoint> getRunPointByUser(Long userId);
}
