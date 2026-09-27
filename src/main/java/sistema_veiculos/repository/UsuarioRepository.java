package sistema_veiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema_veiculos.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}
