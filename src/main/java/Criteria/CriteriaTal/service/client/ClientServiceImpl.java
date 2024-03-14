package Criteria.CriteriaTal.service.client;

import Criteria.CriteriaTal.models.Client;
import Criteria.CriteriaTal.models.dto.ClientDTO;
import Criteria.CriteriaTal.models.helpers.Gender;
import Criteria.CriteriaTal.repository.client.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("ClientServiceImpl")
@AllArgsConstructor
@Slf4j
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
        clientRepository.findClientByEmail(client.getEmail()).ifPresentOrElse(s->{
            log.error(s.getEmail() + " Already Exist");

        }, () ->{
            log.info("Inserting student "+ client);
            clientRepository.save(client);
        });

    }
}
