package Criteria.CriteriaTal.controller.product;

import Criteria.CriteriaTal.models.Client;
import Criteria.CriteriaTal.models.Product;
import Criteria.CriteriaTal.models.dto.ProductDTO;
import Criteria.CriteriaTal.models.response.ProductClientResponse;
import Criteria.CriteriaTal.repository.product.ProductRepository;
import Criteria.CriteriaTal.service.client.IClientService;
import Criteria.CriteriaTal.service.product.IProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService iProductService;
    private final IClientService iClientService;

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
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO product){
        iProductService.createProduct(product);
        return product != null?ResponseEntity.ok(product):ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id")Long id){
        iProductService.deleteProductById(id);
        return ResponseEntity.ok(String.format("%s %s","Eliminado producto con ID",id));
    }

    @Transactional(readOnly = true)
    @GetMapping("find-by-client/{clienid}")
    public ResponseEntity<ProductClientResponse> findProductByClientId(@PathVariable("clienid") Long clientId){
        Client clientToFind = iClientService.findClientById(clientId);
        ProductClientResponse response = new ProductClientResponse(
                String.format("%s %s", clientToFind.getName(),clientToFind.getLastName()),
                iProductService.findProductsByClientId(clientToFind.getId())
        );

        return ResponseEntity.ok(response);
    }
}
