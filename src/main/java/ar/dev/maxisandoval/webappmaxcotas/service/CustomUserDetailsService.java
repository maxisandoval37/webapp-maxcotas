package ar.dev.maxisandoval.webappmaxcotas.service;

import ar.dev.maxisandoval.webappmaxcotas.model.Usuario;
import ar.dev.maxisandoval.webappmaxcotas.model.Veterinario;
import ar.dev.maxisandoval.webappmaxcotas.repository.MascotaRepository;
import ar.dev.maxisandoval.webappmaxcotas.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final MascotaRepository mascotaRepository;

    private final String ROL_LECTURA = "ROL_LECTURA";
    private final String ROL_VETERINARIO = "ROL_VETERINARIO";

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
        usuario.setRol(ROL_LECTURA);

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

        usuario.setRol(ROL_VETERINARIO);
        usuario.setVeterinario(veterinario);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void eliminarUsuario(Long id){
        Optional<Usuario> usuarioActual = usuarioRepository.findById(id);
        String usernameActual = SecurityContextHolder.getContext().getAuthentication().getName();

        if (usuarioActual.isPresent()) {

            //Sesion Activa: No permitimos eliminar el usuario que tiene la sesión actual abierta
            if (usernameActual.equals(usuarioActual.get().getUsername())) {
                throw new IllegalArgumentException("No se puede eliminar el usuario actualmente autenticado en la sesión!");
            }

            //Es veterinario: Borramos las mascotas asociadas que tiene el veterinario (caso practico)
            if (usuarioActual.get().getVeterinario() != null){
                Veterinario veterinario = usuarioActual.get().getVeterinario();
                mascotaRepository.deleteByVeterinario(veterinario);
            }

        }

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