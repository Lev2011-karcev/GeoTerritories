package com.example.GEOhoi.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class RunPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double latitude;  // широта
    private double longitude; // долгота
    private LocalDateTime timestamp; // время записи точки

    @ManyToOne
    private user user; // связь с пользователем

    // Конструктор без id
    public RunPoint(double latitude, double longitude, LocalDateTime timestamp, user user) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
        this.user = user;
    }

    // Метод для JSON, который фронт легко воспринимает
    public RunPointDTO toDTO() {
        return new RunPointDTO(id, latitude, longitude, timestamp, user.getId());
    }

    // Внутренний класс DTO
    public static class RunPointDTO {
        public Long id;
        public double latitude;
        public double longitude;
        public LocalDateTime timestamp;
        public Long userId;

        public RunPointDTO(Long id, double latitude, double longitude, LocalDateTime timestamp, Long userId) {
            this.id = id;
            this.latitude = latitude;
            this.longitude = longitude;
            this.timestamp = timestamp;
            this.userId = userId;
        }
    }
}