package Criteria.CriteriaTal.controller;

import Criteria.CriteriaTal.models.Client;
import Criteria.CriteriaTal.models.dto.ClientDTO;
import Criteria.CriteriaTal.service.client.IClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client/pagiable")
@AllArgsConstructor
public class ClientController {

    private final IClientService iClientService;
    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO){
        iClientService.createClient(clientDTO);
        return clientDTO != null ? ResponseEntity.ok(clientDTO) : ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clientList = iClientService.findAllClient();
        return !clientList.isEmpty()? ResponseEntity.ok(clientList): ResponseEntity.noContent().build();
    }
}
