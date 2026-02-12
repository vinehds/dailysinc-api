package com.vinehds.dailysinc.controller;

import com.vinehds.dailysinc.controller.dto.DeveloperDTO;
import com.vinehds.dailysinc.service.DeveloperService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    @GetMapping
    public ResponseEntity<List<DeveloperDTO>> findAll() {
        return ResponseEntity.ok().body(developerService.getAllDevelopers().stream().map(DeveloperDTO::fromEntity).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeveloperDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(DeveloperDTO.fromEntity(developerService.getDeveloperById(id)));
    }

    @PostMapping
    public ResponseEntity<DeveloperDTO> insert(@RequestBody @Valid DeveloperDTO dto) {

        DeveloperDTO developerInserted = DeveloperDTO.fromEntity(developerService.insertDeveloper(dto.toEntity()));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(developerInserted.id())
                .toUri();
        return ResponseEntity.created(uri).body(developerInserted);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeveloperDTO> update(@PathVariable Long id, @RequestBody @Valid DeveloperDTO obj){
        obj = DeveloperDTO.fromEntity(developerService.updateDeveloper(id, obj));
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        developerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
