package Criteria.CriteriaTal.controller.client;

import Criteria.CriteriaTal.models.Client;
import Criteria.CriteriaTal.models.Product;
import Criteria.CriteriaTal.models.dto.ClientDTO;
import Criteria.CriteriaTal.service.client.IClientService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService iClientService;
    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO){
        iClientService.createClient(clientDTO);
        return clientDTO != null ? ResponseEntity.ok(clientDTO) : ResponseEntity.notFound().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Client> findProductById(@PathVariable("id") Long id){
        Client client = iClientService.findClientById(id);
        return client != null ? ResponseEntity.ok(client):ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clientList = iClientService.findAllClient();
        return !clientList.isEmpty()? ResponseEntity.ok(clientList): ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id")Long id){
        iClientService.deleteClientById(id);
        return ResponseEntity.ok(String.format("%s %s","Eliminado cliente con ID",id));
    }
}
