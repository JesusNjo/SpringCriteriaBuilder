package Criteria.CriteriaTal.service.client;

import Criteria.CriteriaTal.models.Client;
import Criteria.CriteriaTal.models.criteria.ClientCriteriaQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("ClientCriteriaHandler")
@Transactional(readOnly = true)
public class ClientCriteriaHandlerQuery {
    private final Logger log = LoggerFactory.getLogger(ClientCriteriaHandlerQuery.class);
    @Autowired
    public EntityManager em;

    @Transactional(readOnly = true)
    public Page<Client> handlerQueryPag(Pageable page, ClientCriteriaQuery clientCriteriaQuery){
        log.debug("{}", clientCriteriaQuery);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
        Root<Client> clients = cq.from(Client.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        if(clientCriteriaQuery.getQuery() != null && !clientCriteriaQuery.getQuery().isEmpty()){
            List<String> query = Arrays.stream(clientCriteriaQuery.getQuery().split(" ")).filter(s-> !s.isEmpty()).toList();
            for (String s: query){
                predicates.add(cb.like(cb.lower(clients.get("name")),"%"+s.toLowerCase()+"%"));
                predicates.add(cb.like(cb.lower(clients.get("lastName")),"%"+s.toLowerCase()+"%"));
            }
        }

        Predicate[] arrayToList = new Predicate[predicates.size()];
        predicates.toArray(arrayToList);

        Predicate finalQuery = cb.or(predicates.toArray(new Predicate[0]));
        cq.where(finalQuery);
        TypedQuery<Client> query = em.createQuery(cq);
        query.setFirstResult(page.getPageNumber()*page.getPageSize());
        query.setMaxResults(page.getPageSize());

        return new PageImpl<Client>(query.getResultList(),page, query.getResultList().size());
    }
}
