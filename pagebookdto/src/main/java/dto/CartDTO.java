package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {


    private String customerEmail;
    ArrayList<MappingDTO> list;

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public ArrayList<MappingDTO> getList() {
        return list;
    }

    public void setList(ArrayList<MappingDTO> list) {
        this.list = list;
    }

    //String productName ;
    //Int quantity ;
    //double price ;
    //String merchantId ;
    //String email;
}
