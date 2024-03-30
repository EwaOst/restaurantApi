package com.example.restaurantApi.controller;

import com.example.restaurantApi.model.RestaurantModel;
import com.example.restaurantApi.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    /**
     * Adds a new restaurant to the system.
     *
     * @param restaurant The restaurant object to be added, provided in the request body.
     * @return ResponseEntity containing the added RestaurantModel object.
     */
    @PostMapping
    public ResponseEntity<RestaurantModel> addRestaurant(@RequestBody RestaurantModel restaurant) {
        return ResponseEntity.ok(restaurantService.addRestaurant(restaurant));
    }

    /**
     * Retrieves all restaurants currently available in the system.
     *
     * @return ResponseEntity with a list of all RestaurantModel objects.
     */
    @GetMapping
    public ResponseEntity<List<RestaurantModel>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    /**
     * Retrieves a specific restaurant by its ID.
     *
     * @param id The ID of the restaurant to retrieve.
     * @return ResponseEntity containing the RestaurantModel object if found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantModel> getRestaurantById(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }

    /**
     * Updates an existing restaurant's details.
     *
     * @param id         The ID of the restaurant to update.
     * @param restaurant The new details for the restaurant provided in the request body.
     * @return ResponseEntity containing the updated RestaurantModel object.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantModel> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantModel restaurant) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(id, restaurant));
    }

    /**
     * Deletes an existing restaurant from the system.
     *
     * @param id The ID of the restaurant to delete.
     * @return ResponseEntity with OK status if the deletion was successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok().build();
    }
}