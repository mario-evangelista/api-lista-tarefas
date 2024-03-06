package br.org.abmnet.abmedicina.service;

import br.org.abmnet.abmedicina.model.Usuario;
import br.org.abmnet.abmedicina.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario signIn(Usuario usuario) {
        Long userId = usuario.getId();
        if (userId == null) {
            usuario.setCriado(LocalDateTime.now());
        }
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        usuario.setModificado(LocalDateTime.now());
        return repository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByEmail(email);
        return usuario.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public Usuario setUserToken(String username, String token) {
        Usuario usuario = (Usuario) loadUserByUsername(username);
        usuario.setToken(token);
        usuario.setUltimoLogin(LocalDateTime.now());
        usuario.setModificado(LocalDateTime.now());
        return repository.save(usuario);
    }

    public Usuario findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UsernameNotFoundException("ERR"));
    }

    public Usuario authenticated() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
