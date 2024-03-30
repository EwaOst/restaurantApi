package com.example.restaurantApi.controller;

import com.example.restaurantApi.model.UserModel;
import com.example.restaurantApi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * Retrieves all users in the system.
     *
     * @return ResponseEntity containing a list of UserModel objects.
     */
    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Retrieves a specific user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return ResponseEntity containing the UserModel object if found, or NotFound status if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new user with the details provided in the request body.
     *
     * @param user The UserModel object provided in the request body.
     * @return ResponseEntity containing the created UserModel object.
     */
    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    /**
     * Updates an existing user identified by the provided ID with the details in the request body.
     *
     * @param id          The ID of the user to update.
     * @param userDetails The new details for the user provided in the request body.
     * @return ResponseEntity containing the updated UserModel object.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Long id, @RequestBody UserModel userDetails) {
        return ResponseEntity.ok(userService.updateUser(id, userDetails));
    }

    /**
     * Deletes an existing user identified by the provided ID.
     *
     * @param id The ID of the user to delete.
     * @return ResponseEntity with OK status if the deletion was successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}