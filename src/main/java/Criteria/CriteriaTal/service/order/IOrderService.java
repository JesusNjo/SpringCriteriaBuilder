package Criteria.CriteriaTal.service.order;

import Criteria.CriteriaTal.models.Order;
import Criteria.CriteriaTal.models.dto.OrderDTO;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface IOrderService {

    public List<Order> getAllOrders();
    public Order findOrderById(Long id);
    public void createOrder(Long id,Long product);
    public void deleteOrderById(Long id);
    public List<Order> findOrderByClientId(Long id);
}
