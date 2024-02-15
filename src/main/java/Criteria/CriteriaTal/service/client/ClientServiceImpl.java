package Criteria.CriteriaTal.service.client;

import Criteria.CriteriaTal.models.Client;
import Criteria.CriteriaTal.models.dto.ClientDTO;
import Criteria.CriteriaTal.models.helpers.Gender;
import Criteria.CriteriaTal.repository.client.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ClientServiceImpl")
@AllArgsConstructor
public class ClientServiceImpl implements IClientService{

    private final ClientRepository clientRepository;
    @Override
    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Client> findAllClient() {
        return clientRepository.findAll();
    }

    @Override
    public void deleteClientById(Long id) {
        Client newClient = findClientById(id);
        clientRepository.delete(newClient);
    }

    @Override
    public void createClient(ClientDTO clientDTO) {
        Client client = Client.builder()
                .name(clientDTO.getName())
                .lastName(clientDTO.getLastName())
                .email(clientDTO.getEmail())
                .city(clientDTO.getCity())
                .gender(Gender.valueOf(clientDTO.getGender()))
                .build();
        clientRepository.save(client);

    }
}
