package sistema_veiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema_veiculos.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}