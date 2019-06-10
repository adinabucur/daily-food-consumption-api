package org.fasttrackit.dailyfoodconsumptionapi.transfer.characteristics;

public class CreateCharacteristicsRequest {

    private String content;
    private long foodId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    @Override
    public String toString() {
        return "CreateCharacteristicsRequest{" +
                "content='" + content + '\'' +
                ", foodId=" + foodId +
                '}';
    }
}
