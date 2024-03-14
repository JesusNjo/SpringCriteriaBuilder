package Criteria.CriteriaTal.repository.client;

import Criteria.CriteriaTal.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {


    @Query("select e from Client e "+
    "WHERE e.email = ?1")
    Optional<Client> findClientByEmail(String email);

}
