package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRatingDTO {

    private String productId;

    private double productRating;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getProductRating() {
        return productRating;
    }

    public void setProductRating(double productRating) {
        this.productRating = productRating;
    }

    @Override
    public String toString() {
        return "ProductRatingDTO{" +
                "productId='" + productId + '\'' +
                ", productRating=" + productRating +
                '}';
    }
}
