package Criteria.CriteriaTal.controller.querys.orderQueryHandler;

import Criteria.CriteriaTal.models.Order;
import Criteria.CriteriaTal.models.criteria.OrderCriteriaQuery;
import Criteria.CriteriaTal.service.order.OrderCriteriaHandlerQuery;
import io.github.jhipster.web.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderQueryHandler {

    private final OrderCriteriaHandlerQuery orderCriteriaHandlerQuery;

    @PostMapping("/query")
    public ResponseEntity<List<Order>> getQueryOrderByCriteriaPage(Pageable pageable,@RequestBody OrderCriteriaQuery orderCriteriaQuery){
        log.debug("REST request to get Order by filter: {}", orderCriteriaQuery);
        Page<Order> page = orderCriteriaHandlerQuery.handle(pageable,orderCriteriaQuery);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
