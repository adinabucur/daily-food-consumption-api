package org.fasttrackit.dailyfoodconsumptionapi.transfer.food;

import javax.validation.constraints.NotBlank;

public class CreateFoodRequest {

   @NotBlank
    private String name;
    private int nutritionDeclaration;
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNutritionDeclaration() {
        return nutritionDeclaration;
    }

    public void setNutritionDeclaration(int nutritionDeclaration) {
        this.nutritionDeclaration = nutritionDeclaration;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CreateFoodRequest{" +
                "name='" + name + '\'' +
                ", nutritionDeclaration=" + nutritionDeclaration +
                ", quantity=" + quantity +
                '}';
    }
}
