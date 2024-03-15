package Criteria.CriteriaTal.service.order;

import Criteria.CriteriaTal.models.Client;
import Criteria.CriteriaTal.models.Order;
import Criteria.CriteriaTal.models.Product;
import Criteria.CriteriaTal.repository.client.ClientRepository;
import Criteria.CriteriaTal.repository.order.OrderRepository;
import Criteria.CriteriaTal.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("OrderServiceImpl")
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService{
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public void createOrder(Integer quantity, Long clientId, Long productId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Order orderCreate = Order.builder()
                .clientId(client)
                .productId(product)
                .quantity(quantity.longValue())
                .build();

        Order savedOrder = orderRepository.save(orderCreate);

        List<Order> clientOrders = client.getOrders();
        clientOrders.add(savedOrder);
        client.setOrders(clientOrders);
        clientRepository.save(client);
    }




    @Override
    public void deleteOrderById(Long id) {

    }

    @Override
    public List<Order> findOrderByClientId(Long cliendId) {
        return orderRepository.findOrderListByClientId(cliendId);
    }
}
