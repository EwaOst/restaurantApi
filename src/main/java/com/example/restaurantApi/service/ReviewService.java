package com.example.restaurantApi.service;

import com.example.restaurantApi.model.ReviewModel;
import com.example.restaurantApi.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    /**
     * Adds a new review to the system. The review is associated with a specific restaurant
     * and possibly a user, as indicated by the foreign keys in the ReviewModel.
     *
     * @param review The review entity containing the content, rating, and associations.
     * @return The saved ReviewModel entity, now including a generated ID and any other changes made during the save process.
     */
    public ReviewModel addReview(ReviewModel review) {
        return reviewRepository.save(review);
    }

    /**
     * Retrieves all reviews for a specific restaurant, identified by the restaurant's ID.
     * This allows for easy aggregation of feedback specific to a restaurant.
     *
     * @param restaurantId The ID of the restaurant for which reviews are being requested.
     * @return A list of ReviewModel entities associated with the specified restaurant.
     */
    public List<ReviewModel> getReviewsByRestaurantId(Long restaurantId) {
        return reviewRepository.findByRestaurantId(restaurantId);
    }

    /**
     * Deletes a specific review from the system. This operation is irreversible and removes the review
     * entirely from the database.
     *
     * @param id The ID of the review to be deleted.
     * @throws RuntimeException If the review to delete is not found, indicating an attempt to delete a non-existent review.
     */
    public void deleteReview(Long id) {
        ReviewModel review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        reviewRepository.delete(review);
    }
}