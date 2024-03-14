package Criteria.CriteriaTal.models.dto;

import Criteria.CriteriaTal.models.helpers.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClientDTO {

    private String name;
    private String lastName;
    private String email;
    private String city;
    private String gender;
    private List<String> order;
}
