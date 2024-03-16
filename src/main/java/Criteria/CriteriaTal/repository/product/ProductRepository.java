package Criteria.CriteriaTal.repository.product;

import Criteria.CriteriaTal.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    @Query("SELECT DISTINCT p FROM Product p " +
            "JOIN Order o ON p.id = o.productId.id " +
            "WHERE o.clientId.id = ?1")
    List<Product> findProductByClientId(Long clientId);












}
