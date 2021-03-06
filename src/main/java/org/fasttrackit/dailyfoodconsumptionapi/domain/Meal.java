package org.fasttrackit.dailyfoodconsumptionapi.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Meal {

    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "meal_food",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private Set<Food> foods = new HashSet<>();

    public void addFood(Food food){
        foods.add(food);
        food.getMeals().add(this);
    }

    public void removeFood (Food food){
        foods.remove(food);
        food.getMeals().remove(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Food> getFoods() {
        return foods;
    }

    public void setFoods(Set<Food> foods) {
        this.foods = foods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return id == meal.id &&
                Objects.equals(user, meal.user) &&
                Objects.equals(foods, meal.foods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, foods);
    }
}
