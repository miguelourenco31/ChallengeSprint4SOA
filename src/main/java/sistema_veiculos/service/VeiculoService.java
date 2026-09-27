package sistema_veiculos.service;

import org.springframework.stereotype.Service;
import sistema_veiculos.model.Veiculo;
import sistema_veiculos.repository.VeiculoRepository;

import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public List<Veiculo> listar() {
        return repository.findAll();
    }

    public Veiculo criar(Veiculo veiculo) {
        return repository.save(veiculo);
    }

    public Veiculo atualizar(Long id, Veiculo novo) {
        Veiculo veiculo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        veiculo.setModelo(novo.getModelo());
        veiculo.setMarca(novo.getMarca());
        veiculo.setPlaca(novo.getPlaca());

        return repository.save(veiculo);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}