package com.pbs.aplikacja.rest.controller;

import com.pbs.aplikacja.model.LoginRequest;
import com.pbs.aplikacja.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // przez kontener Springa REST-owy kontroler obsługujący sieciowe żądania
public class LoginRestController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    public ResponseEntity<Void> tryLogin(@RequestBody LoginRequest loginRequest) {
        // Zrobić sprawdzanie
        return null;
    }

    @PostMapping("/register/")
    public ResponseEntity<Void> tryRegister(@Valid @RequestBody LoginRequest loginRequest) {
        // Zrobić sprawdzanie
        return null;
    }


}
