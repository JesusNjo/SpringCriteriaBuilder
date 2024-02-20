package Criteria.CriteriaTal.service.client;

import Criteria.CriteriaTal.models.Client;
import Criteria.CriteriaTal.models.dto.ClientDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClientService {

    public Client findClientById(Long id);

    public List<Client> findAllClient();

    public void deleteClientById(Long id);

    public void createClient(ClientDTO clientDTO);
}
