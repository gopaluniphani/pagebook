package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantDTO {

    private  String merchantEmail;
    private String merchantName;
    private double merchantRating;
    private String password;



    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getEmail() {
        return merchantEmail;
    }

    public void setEmail(String email) {
        this.merchantEmail = email;
    }

    public double getMerchantRating() {
        return merchantRating;
    }

    public void setMerchantRating(double merchantRating) {
        this.merchantRating = merchantRating;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //    String merchantId ;
//
//    String merchantName ;
//
//    String email ;
//
//    Double merchantRating ;
}
