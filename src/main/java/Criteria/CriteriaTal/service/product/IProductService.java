package Criteria.CriteriaTal.service.product;

import Criteria.CriteriaTal.models.Product;
import Criteria.CriteriaTal.models.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    public Product findProductById(Long id);
    public List<Product> findAllProducts();
    public void createProduct(ProductDTO product);
    public void deleteProductById(Long id);
}
