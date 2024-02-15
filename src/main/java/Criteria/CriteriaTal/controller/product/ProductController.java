package Criteria.CriteriaTal.controller.product;

import Criteria.CriteriaTal.models.Product;
import Criteria.CriteriaTal.models.dto.ProductDTO;
import Criteria.CriteriaTal.repository.product.ProductRepository;
import Criteria.CriteriaTal.service.product.IProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService iProductService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable("id") Long id){
        Product product = iProductService.findProductById(id);
        return product != null ? ResponseEntity.ok(product):ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity<List<?>> findAllProducts(){
        List<Product> productList = iProductService.findAllProducts();
        return !productList.isEmpty()?ResponseEntity.ok(productList): ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO product){
        iProductService.createProduct(product);

        return ResponseEntity.ok(Product.builder()
                .name(product.getName())
                .price(product.getPrice())
                .build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id")Long id){
        iProductService.deleteProductById(id);
        return ResponseEntity.ok(String.format("%s %s","Eliminado producto con ID",id));
    }
}
