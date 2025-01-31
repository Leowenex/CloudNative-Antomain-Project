package org.antomain.user_service.controller;

import lombok.RequiredArgsConstructor;
import org.antomain.user_service.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    //Get image bytes by id
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable String id) {
        return ResponseEntity.ok(imageService.getImageById(id));
    }
}
