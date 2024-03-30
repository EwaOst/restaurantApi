package com.example.restaurantApi.controller;

import com.example.restaurantApi.model.PhotoModel;
import com.example.restaurantApi.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;


    @PostMapping("/{restaurantId}/photos")
    public ResponseEntity<PhotoModel> addPhotoToRestaurant(@PathVariable Long restaurantId, @RequestParam("photo") MultipartFile photo) throws IOException {
        byte[] photoData = photo.getBytes();
        PhotoModel savePhoto = photoService.addPhotoForRestaurant(restaurantId, photoData);
        return ResponseEntity.ok(savePhoto);
    }


    @GetMapping("/{restaurantId}/photos")
    public ResponseEntity<List<PhotoModel>> getPhotosByRestaurantId(@PathVariable Long restaurantId) {
        List<PhotoModel> photos = photoService.getPhotosByRestaurantId(restaurantId);
        return ResponseEntity.ok(photos);
    }


    @GetMapping("/{restaurantId}/photos/{photoId}")
    public ResponseEntity<byte[]> getPhotoByIdAndRestaurantId(@PathVariable Long restaurantId, @PathVariable Long photoId) {
        PhotoModel photo = photoService.getPhotoByIdAndRestaurantId(restaurantId, photoId);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(photo.getData());
    }


}
