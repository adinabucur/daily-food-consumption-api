package org.fasttrackit.dailyfoodconsumptionapi.transfer.food;

public class GetFoodsRequest {

    private String partialName;
    private Integer minimumNutritionDeclaration;
    private Integer maximumNutritionDeclaration;
    private Integer minimumQuantity;

    public String getPartialName() {
        return partialName;
    }

    public void setPartialName(String partialName) {
        this.partialName = partialName;
    }

    public Integer getMinimumNutritionDeclaration() {
        return minimumNutritionDeclaration;
    }

    public void setMinimumNutritionDeclaration(Integer minimumNutritionDeclaration) {
        this.minimumNutritionDeclaration = minimumNutritionDeclaration;
    }

    public Integer getMaximumNutritionDeclaration() {
        return maximumNutritionDeclaration;
    }

    public void setMaximumNutritionDeclaration(Integer maximumNutritionDeclaration) {
        this.maximumNutritionDeclaration = maximumNutritionDeclaration;
    }

    public Integer getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(Integer minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    @Override
    public String toString() {
        return "GetFoodsRequest{" +
                "partialName='" + partialName + '\'' +
                ", minimumNutritionDeclaration=" + minimumNutritionDeclaration +
                ", maximumNutritionDeclaration=" + maximumNutritionDeclaration +
                ", minimumQuantity=" + minimumQuantity +
                '}';
    }
}
