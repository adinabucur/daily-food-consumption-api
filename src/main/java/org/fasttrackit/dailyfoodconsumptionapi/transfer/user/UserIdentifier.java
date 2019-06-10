package org.fasttrackit.dailyfoodconsumptionapi.transfer.user;

import java.util.Objects;

public class UserIdentifier {

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
        UserIdentifier that = (UserIdentifier) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserIdentifier{" +
                "id=" + id +
                '}';
    }
}
