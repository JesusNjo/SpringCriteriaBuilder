package Criteria.CriteriaTal.models;

import Criteria.CriteriaTal.models.helpers.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "clients")
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 10)
    @NotBlank
    private String name;
    @Column(name = "last_name")
    @Size(max = 10)
    private String lastName;
    @Email
    @Size(max = 50)
    private String email;
    private String city;
    private Gender gender;
    @JsonIgnore
    @OneToMany(mappedBy = "clientId")
    List<Order> orders;
}
