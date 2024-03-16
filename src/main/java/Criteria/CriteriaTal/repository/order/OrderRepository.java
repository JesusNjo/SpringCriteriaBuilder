package Criteria.CriteriaTal.repository.order;

import Criteria.CriteriaTal.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {


    @Query("SELECT o FROM Order o " +
            "LEFT JOIN FETCH o.clientId c " +
            "WHERE c.id = ?1")
    List<Order> findOrderListByClientId(Long clientId);


    @Query("select o from Order o "+
          "left join fetch o.clientId c "+
           "WHERE c.id = ?1")
    Optional<Order> findOrderByClientId(Long id);


}
