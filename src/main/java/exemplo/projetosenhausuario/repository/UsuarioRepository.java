package exemplo.projetosenhausuario.repository;

import exemplo.projetosenhausuario.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    public Optional<UsuarioModel> findByLogin(String login);
}
