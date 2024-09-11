package adea.adea.service;

import adea.adea.entity.SecurityUser;
import adea.adea.entity.Usuario;
import adea.adea.repository.UsuarioRepository;
import java.util.Date;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
    
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(login);
        if(usuario == null){
            throw new UsernameNotFoundException("Login no encontrado");
        }
        
        return new SecurityUser(usuario);
    }
    
}
