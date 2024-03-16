package Criteria.CriteriaTal.service.product;

import Criteria.CriteriaTal.models.Product;
import Criteria.CriteriaTal.models.dto.ProductDTO;
import Criteria.CriteriaTal.repository.product.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductServiceImpl")
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService{

    private final ProductRepository productRepository;
    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void createProduct(ProductDTO product) {
        Product productN = Product.builder().
                name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
        productRepository.save(productN);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.delete(findProductById(id));
    }

    @Override
    public List<Product> findProductsByClientId(Long clientId) {
        return productRepository.findProductByClientId(clientId);
    }

}
