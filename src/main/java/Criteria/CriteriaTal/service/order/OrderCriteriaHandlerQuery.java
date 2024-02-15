package Criteria.CriteriaTal.service.order;

import Criteria.CriteriaTal.models.Client;
import Criteria.CriteriaTal.models.Order;
import Criteria.CriteriaTal.models.Product;
import Criteria.CriteriaTal.models.criteria.OrderCriteriaQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("OrderCriteriaHandlerQuery")
@Slf4j
@Transactional(readOnly = true)

public class OrderCriteriaHandlerQuery {

    @Autowired
    private EntityManager em;

    @Transactional(readOnly = true)
    public Page<Order> handle(Pageable pageable, OrderCriteriaQuery orderCriteriaQuery) {
        log.debug("find by query{}", orderCriteriaQuery);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> order = cq.from(Order.class);

        List<Predicate> predicates = new ArrayList<>();

        if (orderCriteriaQuery.getQuery() != null && !orderCriteriaQuery.getQuery().isEmpty()) {
            List<String> search = Arrays.stream(orderCriteriaQuery.getQuery().split(" "))
                    .filter(o -> !o.isEmpty()).toList();

            Join<Order, Client> clientJoin = order.join("client", JoinType.INNER);
            Join<Order, Product> productJoin = order.join("product", JoinType.INNER);

            for (String o : search) {
                predicates.add(cb.like(cb.lower(clientJoin.get("name")), "%" + o.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(clientJoin.get("lastName")),"%"+o.toLowerCase()+"%"));
                predicates.add(cb.like(cb.lower(clientJoin.get("city")),"%"+o.toLowerCase()+"%"));
                predicates.add(cb.like(cb.lower(productJoin.get("name")),"%"+o.toLowerCase()+"%"));
            }
        }

        Predicate[] arrayToList = new Predicate[predicates.size()];
        predicates.toArray(arrayToList);

        Predicate finalQuery = cb.or(predicates.toArray(new Predicate[0]));
        cq.where(finalQuery);

        TypedQuery<Order> query = em.createQuery(cq);
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());
        return new PageImpl<>(query.getResultList(), pageable, query.getResultList().size());
    }

}
