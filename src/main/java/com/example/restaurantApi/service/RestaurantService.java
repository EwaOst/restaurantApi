package com.example.restaurantApi.service;

import com.example.restaurantApi.model.RestaurantModel;
import com.example.restaurantApi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    /**
     * Adds a new restaurant to the system.
     *
     * @param restaurant The restaurant entity to be added.
     * @return The saved RestaurantModel entity.
     */
    public RestaurantModel addRestaurant(RestaurantModel restaurant) {
        return restaurantRepository.save(restaurant);
    }

    /**
     * Retrieves all restaurants currently available in the system.
     *
     * @return A list of all RestaurantModel entities.
     */
    public List<RestaurantModel> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    /**
     * Retrieves a specific restaurant by its ID.
     *
     * @param id The ID of the restaurant to retrieve.
     * @return The RestaurantModel entity if found.
     * @throws RuntimeException If the restaurant is not found.
     */
    public RestaurantModel getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    /**
     * Updates the details of an existing restaurant.
     *
     * @param id                The ID of the restaurant to update.
     * @param restaurantDetails The new details for the restaurant.
     * @return The updated RestaurantModel entity.
     * @throws RuntimeException If the restaurant to update is not found.
     */
    public RestaurantModel updateRestaurant(Long id, RestaurantModel restaurantDetails) {
        RestaurantModel restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        restaurant.setName(restaurantDetails.getName());
        restaurant.setAddress(restaurantDetails.getAddress());
        restaurant.setCuisineType(restaurantDetails.getCuisineType());
        restaurant.setOpeningHours(restaurantDetails.getOpeningHours());
        restaurant.setPhotos(restaurantDetails.getPhotos());
        restaurant.setRating(restaurantDetails.getRating());

        return restaurantRepository.save(restaurant);
    }

    /**
     * Deletes an existing restaurant from the system.
     *
     * @param id The ID of the restaurant to delete.
     * @throws RuntimeException If the restaurant to delete is not found.
     */
    public void deleteRestaurant(Long id) {
        RestaurantModel restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        restaurantRepository.delete(restaurant);
    }
}