package com.example.restaurantApi.controller;

import com.example.restaurantApi.model.ReviewModel;
import com.example.restaurantApi.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * Adds a new review for a restaurant.
     *
     * @param review The review object provided in the request body, containing the content, rating, and associated restaurant and user IDs.
     * @return ResponseEntity containing the added ReviewModel object.
     */
    @PostMapping
    public ResponseEntity<ReviewModel> addReview(@RequestBody ReviewModel review) {
        return ResponseEntity.ok(reviewService.addReview(review));
    }

    /**
     * Retrieves all reviews associated with a specific restaurant.
     *
     * @param restaurantId The ID of the restaurant for which reviews are being requested.
     * @return ResponseEntity with a list of ReviewModel objects associated with the specified restaurant.
     */
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<ReviewModel>> getReviewsByRestaurantId(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(reviewService.getReviewsByRestaurantId(restaurantId));
    }

    /**
     * Deletes a review from the system.
     *
     * @param id The ID of the review to be deleted.
     * @return ResponseEntity with OK status if the deletion was successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok().build();
    }
}