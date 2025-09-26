package com.example.GEOhoi.Controller;

import com.example.GEOhoi.Model.RunPoint;
import com.example.GEOhoi.Model.user;
import com.example.GEOhoi.Repository.RunPointRepo;
import com.example.GEOhoi.Repository.UserRepo;
import jakarta.persistence.Lob;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class controller {
    @Autowired
    UserRepo repo;
    @Autowired
    RunPointRepo repo_point;
    @GetMapping("/login-succses")
    public String loginSuccses(OAuth2AuthenticationToken authentication){
        var attributes = authentication.getPrincipal().getAttributes();
        String provider = authentication.getAuthorizedClientRegistrationId(); // google
        String providerId = (String) attributes.get("sub"); // уникальный ID от Google
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        String avatarUrl = (String) attributes.get("picture");
        Optional<Boolean> user = Optional.of(repo.findByProviderId(providerId));
        if (user.isEmpty()){
            user bitr = new user(provider, providerId, email, name, avatarUrl);
            return "Пользователь успешно создан";
        }else{
            return "Пользователь уже создан";
        }
    }
    @GetMapping("/users/{userId}/runpoints")
    public List<RunPoint.RunPointDTO> getRunPointsById(@PathVariable Long userId){
        return repo_point.getRunPointByUser(userId)
                .stream()
                .map(RunPoint::toDTO)
                .collect(Collectors.toList());
    }
    @PostMapping("/user/{userId}/NewPoints")
    public String newPoints(@RequestBody RunPoint point){
        repo_point.save(point);
        return "точка сохранена";
    }
    @GetMapping("/users/runpoints")
    public List<RunPoint.RunPointDTO> getRunPointsById(){
        return repo_point.findAll()
                .stream()
                .map(RunPoint::toDTO)
                .collect(Collectors.toList());
    }
}
