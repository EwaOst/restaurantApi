package com.example.restaurantApi.service;

import com.example.restaurantApi.model.PhotoModel;
import com.example.restaurantApi.model.RestaurantModel;
import com.example.restaurantApi.repository.PhotoRepository;
import com.example.restaurantApi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhotoService {


    private final String PHOTO_SAVE_PATH = "C:\\Users\\mail\\Desktop\\zapisaneZdjecia";
    private final PhotoRepository photoRepository;
    private final RestaurantRepository restaurantRepository;


    public PhotoModel addPhotoForRestaurant(Long restaurantId, byte[] photoData) {
        RestaurantModel restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        PhotoModel photo = new PhotoModel();
        photo.setData(photoData);
        PhotoModel savedPhoto = photoRepository.save(photo);
        restaurant.getPhotos().add(savedPhoto);
        restaurantRepository.save(restaurant);
        return savedPhoto;

    }


    public List<PhotoModel> getPhotosByRestaurantId(Long restaurantId) {
        RestaurantModel restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        return restaurant.getPhotos();
    }


    public PhotoModel getPhotoByIdAndRestaurantId(Long restaurantId, Long photoId) {
        RestaurantModel restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        return restaurant.getPhotos().stream()
                .filter(photo -> photo.getId().equals(photoId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Photo not found or does not belong to this restaurant"));

    }

    @Scheduled(fixedDelay = 60000)
    public void savePhotoAutomatically() throws IOException {
        saveRandomPhoto();
    }


    private void saveRandomPhoto() throws IOException {
        List<RestaurantModel> allRestaurants = restaurantRepository.findAll();
        for (RestaurantModel restaurant : allRestaurants) {
            if (!restaurant.getPhotos().isEmpty()) {
                PhotoModel photo = restaurant.getPhotos().get(0);
                byte[] photoData = photo.getData();

                savePhotoToFile(photoData, restaurant.getId() + "_" + photo.getId() + ".jpg");
            }
        }
        log.info("wywoływana operacja co 1 minute np zrzutu zdjęć do katalogu");
    }


    private void savePhotoToFile(byte[] photoData, String fileName) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(PHOTO_SAVE_PATH + fileName)) {
            fos.write(photoData);
            log.info("Zapisano zdjęcie: {}", fileName);
        }

    }


}






