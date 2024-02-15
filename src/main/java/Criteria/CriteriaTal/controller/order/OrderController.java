package Criteria.CriteriaTal.controller.order;

import Criteria.CriteriaTal.models.Order;
import Criteria.CriteriaTal.service.order.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    @PostMapping
    public String createOrder(@RequestParam Long clientId, @RequestParam Long productId){
        iOrderService.createOrder(clientId,productId);
        return "Orden creada.";
    }
    @GetMapping
    public ResponseEntity<List<?>> findAllOrders(){
        List<Order> orderList = iOrderService.getAllOrders();
        return !orderList.isEmpty()?ResponseEntity.ok(orderList):ResponseEntity.noContent().build();
    }
    @GetMapping("/{clientid}")
    public ResponseEntity<List<Order>> findByClient(@PathVariable("clientid") Long clientid){
        List<Order> byClient = iOrderService.findOrderByClientId(clientid);
        return !byClient.isEmpty()?ResponseEntity.ok(byClient):ResponseEntity.noContent().build();

    }
}
