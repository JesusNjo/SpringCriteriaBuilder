package Criteria.CriteriaTal.controller.pagination;

import Criteria.CriteriaTal.models.Order;
import Criteria.CriteriaTal.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderPaginationHandler {

    private final OrderRepository orderRepository;

    @GetMapping("/pagination")
    public ResponseEntity<List<Order>> findOrderPagination(Pageable pageable){
        Page<Order> page = orderRepository.findAll(pageable);
        return !page.isEmpty()? ResponseEntity.ok(page.getContent()) : ResponseEntity.noContent().build();
    }
}
