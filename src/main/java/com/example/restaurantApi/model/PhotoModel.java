package com.example.restaurantApi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PhotoModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] data;
}
