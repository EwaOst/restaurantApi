package com.example.restaurantApi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Data
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;
    @Enumerated(EnumType.STRING)

    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<ReservationModel> reservations;

    @OneToMany(mappedBy = "user")
    private List<ReviewModel> reviews;
}
