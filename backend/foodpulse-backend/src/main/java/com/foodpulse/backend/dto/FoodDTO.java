package com.foodpulse.backend.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for Food items.
 */
public class FoodDTO {

    private Long id;

    @NotBlank(message = "Food name is required")
    private String foodName;

    @NotBlank(message = "Quantity is required")
    private String quantity;

    @NotBlank(message = "Location is required")
    private String location;

    public FoodDTO() {}

    public FoodDTO(Long id, String foodName, String quantity, String location) {
        this.id = id;
        this.foodName = foodName;
        this.quantity = quantity;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
