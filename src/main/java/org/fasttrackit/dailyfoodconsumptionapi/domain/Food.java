package org.fasttrackit.dailyfoodconsumptionapi.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 1, max = 300)
    private String name;
    private int nutritionDeclaration;
    private int quantity;

    @ManyToMany(mappedBy = "foods")
    private Set<Meal> meals = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }

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
}
