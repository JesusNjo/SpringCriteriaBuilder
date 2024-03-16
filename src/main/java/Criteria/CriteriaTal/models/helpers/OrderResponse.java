package Criteria.CriteriaTal.models.helpers;
import Criteria.CriteriaTal.models.Order;
import Criteria.CriteriaTal.models.helpers.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponse {

    private List<Order> orders;
    private String response;
}
