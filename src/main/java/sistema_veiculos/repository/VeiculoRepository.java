package sistema_veiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema_veiculos.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}