package Criteria.CriteriaTal.controller.order;

import Criteria.CriteriaTal.models.Order;
import Criteria.CriteriaTal.models.Product;
import Criteria.CriteriaTal.service.order.IOrderService;
import Criteria.CriteriaTal.service.product.IProductService;
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
    @Autowired
    private IProductService productService;

    @PostMapping
    public String createOrder(@RequestParam Integer quantity,@RequestParam Long clientId, @RequestParam Long productId){
        iOrderService.createOrder(quantity,clientId,productId);
        Product productSearch = productService.findProductById(productId);

        double amountT = quantity * productSearch.getPrice();
        return "Orden creada. Total Amount: " + amountT;
    }
    @GetMapping
    public ResponseEntity<List<Order>> findAllOrders(){
        List<Order> orderList = iOrderService.getAllOrders();
        return !orderList.isEmpty()?ResponseEntity.ok(orderList):ResponseEntity.noContent().build();
    }
    @GetMapping("/{clientid}")
    public ResponseEntity<List<Order>> findByClient(@PathVariable("clientid") Long clientid){
        List<Order> byClient = iOrderService.findOrderByClientId(clientid);
        return !byClient.isEmpty()?ResponseEntity.ok(byClient):ResponseEntity.noContent().build();

    }
}
