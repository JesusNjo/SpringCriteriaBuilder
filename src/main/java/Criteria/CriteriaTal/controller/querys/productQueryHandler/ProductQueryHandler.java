package Criteria.CriteriaTal.controller.querys.productQueryHandler;

import Criteria.CriteriaTal.models.Product;
import Criteria.CriteriaTal.models.criteria.ProductCriteriaQuery;
import Criteria.CriteriaTal.service.product.ProductCriteriaHandlerQuery;
import io.github.jhipster.web.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductQueryHandler {

    private final ProductCriteriaHandlerQuery productCriteriaHandlerQuery;

    @PostMapping("/pagination")
    public ResponseEntity<List<Product>> getQueryProductByCriteriaPage(Pageable pageable,@RequestBody ProductCriteriaQuery productCriteriaQuery){
        log.debug("REST request to get Client by filter: {}", productCriteriaQuery);
        Page<Product> page = productCriteriaHandlerQuery.handler(pageable,productCriteriaQuery);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(),page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
