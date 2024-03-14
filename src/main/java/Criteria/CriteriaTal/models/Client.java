package Criteria.CriteriaTal.models;

import Criteria.CriteriaTal.models.helpers.Gender;
import jakarta.persistence.*;
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

    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String city;
    private Gender gender;
    @OneToMany(mappedBy = "clientId", cascade = CascadeType.ALL)
    List<Order> orders;
}
