package com.example.restaurantApi.service;

import com.example.restaurantApi.model.TableModel;
import com.example.restaurantApi.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;

    /**
     * Retrieves all tables from the database. This includes every table across all restaurants,
     * providing a comprehensive list of available dining spots.
     *
     * @return A list of all TableModel entities in the system.
     */
    public List<TableModel> getAllTables() {
        return tableRepository.findAll();
    }

    /**
     * Fetches a specific table by its unique ID. This allows for targeted operations on or queries about
     * a single table, such as checking its availability or characteristics.
     *
     * @param id The unique identifier of the table to retrieve.
     * @return An Optional containing the TableModel if found, or empty if not found.
     */
    public Optional<TableModel> getTableById(Long id) {
        return tableRepository.findById(id);
    }

    /**
     * Adds a new table to the system. This can be used to increase the capacity of a restaurant or to
     * introduce new seating arrangements.
     *
     * @param table The TableModel entity containing the details of the new table.
     * @return The saved TableModel entity, now including a generated ID.
     */
    public TableModel createTable(TableModel table) {
        return tableRepository.save(table);
    }

    /**
     * Updates the details of an existing table. This method allows for modifications to table characteristics,
     * such as its number, seating capacity, status (e.g., available or reserved), and layout plan.
     * Note: Modifying the assigned restaurant requires additional logic not covered here.
     *
     * @param id           The ID of the table to be updated.
     * @param tableDetails A TableModel entity containing the updated details of the table.
     * @return The updated TableModel entity.
     * @throws RuntimeException If the table to update is not found.
     */
    public TableModel updateTable(Long id, TableModel tableDetails) {
        TableModel table = tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found"));
        table.setTableNumber(tableDetails.getTableNumber());
        table.setSeatingCapacity(tableDetails.getSeatingCapacity());
        table.setStatus(tableDetails.getStatus());
        table.setLayoutPlan(tableDetails.getLayoutPlan());
        return tableRepository.save(table);
    }

    /**
     * Removes a table from the system. This operation deletes the table entity entirely,
     * which may be necessary in cases of restructuring or decommissioning seating areas.
     *
     * @param id The ID of the table to delete.
     * @throws RuntimeException If the table to delete is not found.
     */
    public void deleteTable(Long id) {
        TableModel table = tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found"));
        tableRepository.delete(table);
    }
}