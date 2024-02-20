package Criteria.CriteriaTal.controller.pagination;

import Criteria.CriteriaTal.models.Product;
import Criteria.CriteriaTal.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductPaginationHandler {

    private final ProductRepository productRepository;

    @GetMapping("/pagination")
    public ResponseEntity<List<Product>> findProductPagination(Pageable pageable){
        Page<Product> page = productRepository.findAll(pageable);
        return !page.isEmpty() ? ResponseEntity.ok(page.getContent()) : ResponseEntity.noContent().build();
    }
}
