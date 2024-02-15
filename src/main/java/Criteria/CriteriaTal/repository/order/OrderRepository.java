package Criteria.CriteriaTal.repository.order;

import Criteria.CriteriaTal.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

   @Query("select o from Order o " +
            "left join fetch o.client c " +
            "WHERE c.id = ?1")
    List<Order> findOrderByClientIdAndProduct(Long clientId);


}
