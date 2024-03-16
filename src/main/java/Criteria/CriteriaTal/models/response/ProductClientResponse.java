package Criteria.CriteriaTal.models.response;


import Criteria.CriteriaTal.models.Product;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductClientResponse {

    private String clientName;
    private List<Product> productList;

}
