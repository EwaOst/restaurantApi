package com.example.restaurantApi.repository;

import com.example.restaurantApi.model.TableModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<TableModel, Long> {
}
