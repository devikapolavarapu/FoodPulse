package com.foodpulse.backend.service;

import com.foodpulse.backend.dto.FoodDTO;

import java.util.List;

/**
 * Service interface for managing food items.
 */
public interface FoodService {

    /**
     * Retrieves all food items.
     *
     * @return list of food items
     */
    List<FoodDTO> getAllFood();

    /**
     * Adds a new food item.
     *
     * @param foodDTO the food item details
     * @return the saved food item DTO
     */
    FoodDTO addFood(FoodDTO foodDTO);

    /**
     * Updates an existing food item.
     *
     * @param id the food item ID
     * @param foodDetailsDTO the updated food details
     * @return the updated food item DTO
     */
    FoodDTO updateFood(Long id, FoodDTO foodDetailsDTO);

    /**
     * Deletes a food item.
     *
     * @param id the food item ID
     */
    void deleteFood(Long id);
}
