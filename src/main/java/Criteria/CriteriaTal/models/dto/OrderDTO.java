package Criteria.CriteriaTal.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {


    private Long clientId;
    private Long productId;
    private Long quantity;
}
