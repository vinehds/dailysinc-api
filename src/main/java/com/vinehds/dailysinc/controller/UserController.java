package com.vinehds.dailysinc.controller;

import com.vinehds.dailysinc.controller.dto.UpdateMeRequestDTO;
import com.vinehds.dailysinc.controller.dto.UserDTO;
import com.vinehds.dailysinc.model.entities.User;
import com.vinehds.dailysinc.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PatchMapping("/me")
    public ResponseEntity<UserDTO> updateMe(@RequestBody @Valid UpdateMeRequestDTO dto, @AuthenticationPrincipal User user) {
        User userUpdated = userService.updateMe(user.getId(), dto);
        return ResponseEntity.ok(UserDTO.fromEntity(userUpdated));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok().body(userService.getAllUsers().stream().map(UserDTO::fromEntity).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(UserDTO.fromEntity(userService.getUserById(id)));
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody @Valid UserDTO dto) {

        UserDTO userInserted = UserDTO.fromEntity(userService.insertUser(dto.toEntity()));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userInserted.id())
                .toUri();
        return ResponseEntity.created(uri).body(userInserted);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody @Valid UserDTO obj){
        obj = UserDTO.fromEntity(userService.updateUser(id, obj));
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
