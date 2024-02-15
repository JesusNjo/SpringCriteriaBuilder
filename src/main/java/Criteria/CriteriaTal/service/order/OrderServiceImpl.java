package Criteria.CriteriaTal.service.order;

import Criteria.CriteriaTal.models.Client;
import Criteria.CriteriaTal.models.Order;
import Criteria.CriteriaTal.models.Product;
import Criteria.CriteriaTal.models.dto.OrderDTO;
import Criteria.CriteriaTal.repository.client.ClientRepository;
import Criteria.CriteriaTal.repository.order.OrderRepository;
import Criteria.CriteriaTal.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public void createOrder(Long clienteId,Long productId) {

        Client client = clientRepository.findById(clienteId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        Order orderCreate = Order.builder()
                .client(client)
                .product(product)
                .quantity(orderRepository.count())
                .build();
        if(orderCreate.getClient().getId() == clienteId){
            orderCreate.setQuantity(orderCreate.getQuantity()+1);
        }
        orderRepository.save(orderCreate);
    }

    @Override
    public void deleteOrderById(Long id) {

    }

    @Override
    public List<Order> findOrderByClientId(Long cliendId) {
        return orderRepository.findOrderByClientIdAndProduct(cliendId);
    }
}
