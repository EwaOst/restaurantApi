package com.example.restaurantApi.controller;

import com.example.restaurantApi.model.TableModel;
import com.example.restaurantApi.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    /**
     * Retrieves a list of all tables in the system.
     *
     * @return ResponseEntity containing a list of TableModel objects.
     */
    @GetMapping
    public ResponseEntity<List<TableModel>> getAllTables() {
        return ResponseEntity.ok(tableService.getAllTables());
    }

    /**
     * Retrieves a specific table by its ID.
     *
     * @param id The ID of the table to retrieve.
     * @return ResponseEntity containing the TableModel object if found, or NotFound status if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TableModel> getTableById(@PathVariable Long id) {
        return tableService.getTableById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new table with the details provided in the request body.
     *
     * @param table The TableModel object provided in the request body.
     * @return ResponseEntity containing the created TableModel object.
     */
    @PostMapping
    public ResponseEntity<TableModel> createTable(@RequestBody TableModel table) {
        return ResponseEntity.ok(tableService.createTable(table));
    }

    /**
     * Updates an existing table identified by the provided ID with the details in the request body.
     *
     * @param id           The ID of the table to update.
     * @param tableDetails The new details for the table provided in the request body.
     * @return ResponseEntity containing the updated TableModel object.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TableModel> updateTable(@PathVariable Long id, @RequestBody TableModel tableDetails) {
        return ResponseEntity.ok(tableService.updateTable(id, tableDetails));
    }

    /**
     * Deletes an existing table identified by the provided ID.
     *
     * @param id The ID of the table to delete.
     * @return ResponseEntity with OK status if the deletion was successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
        tableService.deleteTable(id);
        return ResponseEntity.ok().build();
    }
}