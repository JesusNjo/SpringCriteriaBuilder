package Criteria.CriteriaTal.models;

import Criteria.CriteriaTal.models.helpers.Gender;
import jakarta.persistence.*;
import lombok.*;

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

    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String city;
    private Gender gender;
}
