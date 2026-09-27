package sistema_veiculos.service;

import org.springframework.stereotype.Service;
import sistema_veiculos.model.Cliente;
import sistema_veiculos.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente criar(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente novo) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(novo.getNome());
        cliente.setEmail(novo.getEmail());
        cliente.setTelefone(novo.getTelefone());

        return repository.save(cliente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}