package adea.adea.repository;

import adea.adea.entity.Usuario;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    List<Usuario> findByNombreContainingIgnoreCase(String keyword);
    List<Usuario> findByStatus(Character status);
    List<Usuario> findByFechaAltaBetween(Date fechaInicio, Date fechaFin);
    Usuario findByLogin(String login);
}
