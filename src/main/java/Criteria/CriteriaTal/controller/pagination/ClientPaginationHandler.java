package Criteria.CriteriaTal.controller.pagination;

import Criteria.CriteriaTal.models.Client;
import Criteria.CriteriaTal.repository.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientPaginationHandler {

    private final ClientRepository clientRepository;
    @GetMapping("/pagination")
    public ResponseEntity<List<Client>> findClientPagination(Pageable pageable){
        Page<Client> page = clientRepository.findAll(pageable);
        return !page.isEmpty()? ResponseEntity.ok(page.getContent()) : ResponseEntity.notFound().build();
    }
}
