package org.antomain.image_service.controller;

import lombok.RequiredArgsConstructor;
import org.antomain.image_service.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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

    @PostMapping
    public ResponseEntity<String> saveImage(@RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(imageService.saveImage(image));
    }
}
