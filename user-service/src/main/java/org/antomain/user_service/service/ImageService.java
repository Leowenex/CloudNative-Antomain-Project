package org.antomain.user_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    public byte[] getImageById(String id) {
        Path path = Paths.get("/images", id);
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
        }
    }

    /**
     * Saves image to filesystem /images directory
     * Creates the directory if it doesn't exist
     * Returns the UUID of the saved image
     * @param image - image bytes
     * @return UUID of the saved image
     */
    public String saveImage(MultipartFile image) {

        String filename = image.getOriginalFilename();
        if (filename == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No file provided");
        }
        String fileType = filename.substring(filename.lastIndexOf(".") + 1);
        if (!fileType.equals("jpg") && !fileType.equals("jpeg") && !fileType.equals("png")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only jpg, jpeg and png files are allowed");
        }

        Path path = Paths.get("/images");
        if (!path.toFile().exists()) {
            if (!path.toFile().mkdir()) {
                throw new RuntimeException("Failed to create images directory");
            }
        }

        String id = UUID.randomUUID()+"."+fileType;
        Path imagePath = Paths.get("/images", id);
        try {
            Files.write(imagePath, image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image");
        }

        return id;
    }
}
