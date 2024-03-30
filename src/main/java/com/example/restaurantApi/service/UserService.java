package com.example.restaurantApi.service;

import com.example.restaurantApi.model.UserModel;
import com.example.restaurantApi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Retrieves a list of all users from the database. This includes every registered user,
     * providing an overview of the user base.
     *
     * @return A list of all UserModel entities.
     */
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Fetches a specific user by their unique ID. This allows for targeted user management operations,
     * such as updating user details or deleting a user account.
     *
     * @param id The unique identifier of the user to retrieve.
     * @return An Optional containing the UserModel if found, or empty if not found.
     */
    public Optional<UserModel> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Adds a new user to the system. This can be used to register new users or add new staff members.
     *
     * @param user The UserModel entity containing the details of the new user.
     * @return The saved UserModel entity, now including a generated ID and any other changes made during the save process.
     */
    public UserModel createUser(UserModel user) {
        return userRepository.save(user);
    }

    /**
     * Updates the details of an existing user. This method allows for comprehensive modifications to a user's profile,
     * including their email, password, first name, last name, and role within the system.
     *
     * @param id          The ID of the user to be updated.
     * @param userDetails A UserModel entity containing the updated details of the user.
     * @return The updated UserModel entity.
     * @throws RuntimeException If the user to update is not found, indicating an attempt to update a non-existent user.
     */
    public UserModel updateUser(Long id, UserModel userDetails) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setRole(userDetails.getRole());
        return userRepository.save(user);
    }

    /**
     * Removes a user from the system. This operation deletes the user entity entirely,
     * which may be necessary in cases of account closure or staff turnover.
     *
     * @param id The ID of the user to delete.
     * @throws RuntimeException If the user to delete is not found, indicating an attempt to delete a non-existent user.
     */
    public void deleteUser(Long id) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        userRepository.delete(user);
    }
}