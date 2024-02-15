package Criteria.CriteriaTal.service.product;

import Criteria.CriteriaTal.models.Client;
import Criteria.CriteriaTal.models.Product;
import Criteria.CriteriaTal.models.criteria.ProductCriteriaQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("ProductCriteriaHandlerQuery")
@Transactional(readOnly = true)
@Slf4j
public class ProductCriteriaHandlerQuery {

    @Autowired
    public EntityManager em;


    @Transactional(readOnly = true)
    public Page<Product> handler(Pageable pageable, ProductCriteriaQuery productCriteriaQuery){
        log.debug("find by query {}", productCriteriaQuery);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        if(productCriteriaQuery.getQuery() != null && !productCriteriaQuery.getQuery().isEmpty()){
            List<String> search = Arrays.stream(productCriteriaQuery.getQuery().split(" ")).filter(s-> !s.isEmpty()).toList();

            for(String s: search){
                predicates.add(cb.like(cb.lower(product.get("name")),"%"+s.toLowerCase()+"%"));
            }
        }
        Predicate[] arrayToList = new Predicate[predicates.size()];
        predicates.toArray(arrayToList);

        Predicate finalQuery = cb.or(predicates.toArray(new Predicate[0]));
        cq.where(finalQuery);
        TypedQuery<Product> query = em.createQuery(cq);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());


        return new PageImpl<Product>(query.getResultList(),pageable,query.getResultList().size());
    }
}
