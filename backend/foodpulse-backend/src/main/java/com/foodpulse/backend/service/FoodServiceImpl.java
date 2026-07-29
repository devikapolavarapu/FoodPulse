package com.foodpulse.backend.service;

import com.foodpulse.backend.dto.FoodDTO;
import com.foodpulse.backend.entity.Food;
import com.foodpulse.backend.exception.ResourceNotFoundException;
import com.foodpulse.backend.repository.FoodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service implementation for Food item management.
 */
@Service
public class FoodServiceImpl implements FoodService {

    private static final Logger log = LoggerFactory.getLogger(FoodServiceImpl.class);

    private final FoodRepository foodRepository;

    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public List<FoodDTO> getAllFood() {
        log.info("Fetching all food items");
        List<Food> foods = foodRepository.findAll();
        List<FoodDTO> dtos = new ArrayList<>();

        for (Food food : foods) {
            dtos.add(new FoodDTO(
                    food.getId(),
                    food.getFoodName(),
                    food.getQuantity(),
                    food.getLocation()
            ));
        }

        return dtos;
    }

    @Override
    public FoodDTO addFood(FoodDTO foodDTO) {
        log.info("Adding new food item: {}", foodDTO.getFoodName());
        Food food = new Food(
                foodDTO.getFoodName(),
                foodDTO.getQuantity(),
                foodDTO.getLocation()
        );

        Food saved = foodRepository.save(food);
        log.info("Food item added with ID: {}", saved.getId());

        return new FoodDTO(
                saved.getId(),
                saved.getFoodName(),
                saved.getQuantity(),
                saved.getLocation()
        );
    }

    @Override
    public FoodDTO updateFood(Long id, FoodDTO foodDetailsDTO) {
        log.info("Updating food item ID: {}", id);
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food item not found with ID: " + id));

        food.setFoodName(foodDetailsDTO.getFoodName());
        food.setQuantity(foodDetailsDTO.getQuantity());
        food.setLocation(foodDetailsDTO.getLocation());

        Food saved = foodRepository.save(food);
        log.info("Food item ID {} updated successfully", id);

        return new FoodDTO(
                saved.getId(),
                saved.getFoodName(),
                saved.getQuantity(),
                saved.getLocation()
        );
    }

    @Override
    public void deleteFood(Long id) {
        log.info("Deleting food item ID: {}", id);
        if (!foodRepository.existsById(id)) {
            log.warn("Food item ID {} not found for deletion", id);
            throw new ResourceNotFoundException("Food item not found with ID: " + id);
        }
        foodRepository.deleteById(id);
        log.info("Food item ID {} deleted successfully", id);
    }
}
