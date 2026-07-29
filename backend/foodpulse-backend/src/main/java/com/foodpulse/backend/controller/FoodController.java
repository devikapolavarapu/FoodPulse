package com.foodpulse.backend.controller;

import com.foodpulse.backend.dto.FoodDTO;
import com.foodpulse.backend.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller exposing endpoints for managing food items.
 */
@RestController
@RequestMapping("/food")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Food Controller", description = "Endpoints for managing mess food items")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    /**
     * Retrieves all food items.
     *
     * @return list of food items
     */
    @GetMapping("/all")
    @Operation(summary = "Get all food items", description = "Retrieves a list of all mess food items.")
    public ResponseEntity<List<FoodDTO>> getAllFood() {
        List<FoodDTO> foods = foodService.getAllFood();
        return ResponseEntity.ok(foods);
    }

    /**
     * Adds a new food item.
     *
     * @param foodDTO the food item details DTO
     * @return the created food item details
     */
    @PostMapping("/add")
    @Operation(summary = "Add a new food item", description = "Saves a new food item details.")
    public ResponseEntity<FoodDTO> addFood(@Valid @RequestBody FoodDTO foodDTO) {
        FoodDTO created = foodService.addFood(foodDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /**
     * Deletes a food item by its ID.
     *
     * @param id the food item ID
     * @return response entity with no content status
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a food item", description = "Deletes a food item by its ID.")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates an existing food item.
     *
     * @param id the food item ID
     * @param foodDetailsDTO the updated food details DTO
     * @return the updated food item details
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a food item", description = "Updates the details of an existing food item.")
    public ResponseEntity<FoodDTO> updateFood(@PathVariable Long id, @Valid @RequestBody FoodDTO foodDetailsDTO) {
        FoodDTO updated = foodService.updateFood(id, foodDetailsDTO);
        return ResponseEntity.ok(updated);
    }
}