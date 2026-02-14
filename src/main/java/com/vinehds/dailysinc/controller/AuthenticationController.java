package com.vinehds.dailysinc.controller;

import com.vinehds.dailysinc.controller.dto.auth.AuthenticationDTO;
import com.vinehds.dailysinc.controller.dto.auth.LoginResponseDTO;
import com.vinehds.dailysinc.controller.dto.auth.MeDTO;
import com.vinehds.dailysinc.controller.dto.auth.RegisterDTO;
import com.vinehds.dailysinc.infra.security.TokenService;
import com.vinehds.dailysinc.model.entities.User;
import com.vinehds.dailysinc.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;
    private final TokenService tokenService;

    // TODO: atualizar dados do usuario logado
    // TODO:
    // TODO:
    // TODO:

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) Objects.requireNonNull(auth.getPrincipal()));

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @GetMapping("/me")
    public ResponseEntity<MeDTO> me(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(MeDTO.fromDeveloper(user));
    }
}
