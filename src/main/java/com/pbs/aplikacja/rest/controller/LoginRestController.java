package com.pbs.aplikacja.rest.controller;

import com.pbs.aplikacja.util.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // przez kontener Springa REST-owy kontroler obsługujący sieciowe żądania
public class LoginRestController {

    @PostMapping("/login/")
    public ResponseEntity<Void> tryLogin(@Valid @RequestBody LoginRequest loginRequest) {
        // Zrobić sprawdzanie
        return null;
    }

    @PostMapping("/register/")
    public ResponseEntity<Void> tryRegister(@Valid @RequestBody LoginRequest loginRequest) {
        // Zrobić sprawdzanie
        return null;
    }


}
