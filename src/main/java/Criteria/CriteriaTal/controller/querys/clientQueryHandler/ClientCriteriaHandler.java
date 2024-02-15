package Criteria.CriteriaTal.controller.querys.clientQueryHandler;

import Criteria.CriteriaTal.models.Client;
import Criteria.CriteriaTal.models.criteria.ClientCriteriaQuery;
import Criteria.CriteriaTal.service.client.ClientCriteriaHandlerQuery;
import io.github.jhipster.web.util.PaginationUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
@Slf4j
public class ClientCriteriaHandler {

   private final ClientCriteriaHandlerQuery clientCriteriaHandlerQuery;

    @PostMapping("/pagination")
    public ResponseEntity<List<Client>> getQueryClientByCriteriaPage(Pageable pageable,@RequestBody ClientCriteriaQuery clientCriteriaQuery){
        log.debug("REST request to get Client by filter: {}", clientCriteriaQuery);
        Page<Client> page = clientCriteriaHandlerQuery.handlerQueryPag(pageable,clientCriteriaQuery);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}



/*Predicate[] predArray = new Predicate[predicates.size()];
        predicates.toArray(predArray);

Predicate finalQuery = cb.or(predicates.toArray(new Predicate[0]));

        cq.where(finalQuery).groupBy(guest.get("id"));

TypedQuery<PGuest> query = entityManager.createQuery(cq);

        query.setFirstResult(page.getPageNumber() * page.getPageSize());
        query.setMaxResults(page.getPageSize());

Page<PGuest> result = new PageImpl<PGuest>(query.getResultList(), page, query.getResultList().size());
        return result;

    }*/
