package ar.dev.maxisandoval.webappmaxcotas.service;

import ar.dev.maxisandoval.webappmaxcotas.model.Usuario;
import ar.dev.maxisandoval.webappmaxcotas.model.Veterinario;
import ar.dev.maxisandoval.webappmaxcotas.repository.UsuarioRepository;
import ar.dev.maxisandoval.webappmaxcotas.repository.VeterinarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("loadUserByUsername: Usuario no encontrado: ".concat(username));
        }

        return User.withUsername(usuario.getUsername())
                .password(usuario.getContrasena())
                .authorities(List.of(new SimpleGrantedAuthority(usuario.getRol())))
                .build();
    }

    public Usuario guardarUsuario(Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findByUsername(usuario.getUsername());
        if (usuarioExistente != null) {
            throw new DataIntegrityViolationException("El usuario ya se encuentra registrado!");
        }

        usuario.setContrasena(passwordEncoder().encode(usuario.getContrasena()));
        usuario.setRol("ROLE_LECTURA");

        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarRolUsuario(Long id, String nuevoRol) {
        Usuario usuario = obtenerUsuarioPorId(id);

        if (usuario == null) {
            throw new UsernameNotFoundException("actualizarRolUsuario: Usuario no encontrado: "+id);
        }

        usuario.setRol(nuevoRol);
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarRolUsuarioVeterinario(Long id, Veterinario veterinario) {
        Usuario usuario = obtenerUsuarioPorId(id);

        if (usuario == null) {
            throw new UsernameNotFoundException("actualizarRolUsuarioVeterinario: Usuario no encontrado: "+id);
        }

        usuario.setVeterinario(veterinario);
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> listarUsuariosRegistrados() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> listarUsuariosRegistradosConVeterinarios() {
        return usuarioRepository.findByVeterinarioIsNotNull();
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontró el usuario: "+id));
    }

    public Usuario obtenerUsuarioPorVeterinario(Veterinario veterinario) {
        return usuarioRepository.findByVeterinario(veterinario);
    }

    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}