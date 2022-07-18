package net.sparkminds.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sparkminds.dto.PastProjectResponseDto;
import net.sparkminds.service.PastProjectService;

@RestController
@RequestMapping("api/pass-project")
@RequiredArgsConstructor
public class PastProjectController {
    
    private final PastProjectService pastProjectService;
    
    @GetMapping
    public ResponseEntity<List<PastProjectResponseDto>> getPastProject() {
        return ResponseEntity.ok().body(pastProjectService.getAllPastProject());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<List<PastProjectResponseDto>> getPastProject(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(pastProjectService.getPastProjectById(id));
    }
    
//    @PostMapping("/add")
//    public ResponseEntity<?> postNewPastProject(@RequestBody PastProjectRequestDto pastProjectRequestDto) {
//        pastProjectService.createPassProject(pastProjectRequestDto);
//       return ResponseEntity.ok().build();
//    };
}
