package org.fasttrackit.dailyfoodconsumptionapi.transfer.food;

import java.util.Objects;

public class FoodIdentifier {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodIdentifier that = (FoodIdentifier) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "FoodIdentifier{" +
                "id=" + id +
                '}';
    }
}
