
package com.example.atv.Service;

import com.example.atv.DTO.UsuarioDTO;
import com.example.atv.Entity.Usuario;
import com.example.atv.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository UsuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return UsuarioRepository.findAll();
    }

    public Optional<UsuarioDTO> getById(Long id) {
        Optional<Usuario> usuarioOptional = UsuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            return Optional.of(usuarioDTO.fromUsuario(usuarioOptional.get()));
        } else {
            return Optional.empty();
        }
    }

    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        Usuario Usuario = usuarioDTO.toUsuario();
        Usuario = UsuarioRepository.save(Usuario);
        return usuarioDTO.fromUsuario(Usuario);
    }

    public Optional<UsuarioDTO> updateUsuario(Long id, UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioOptional = UsuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setNome(usuarioDTO.getNome());
            usuario.setSobrenome(usuarioDTO.getSobrenome());
            usuario.setCpf(usuarioDTO.getCpf());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setUsername(usuarioDTO.getUsername());
            usuario.setSenha(usuarioDTO.getSenha());
            usuario.setDataNascimento(usuarioDTO.getDataNascimento());

            Usuario usuarioUpdate = UsuarioRepository.save(usuario);

            return Optional.of(usuarioDTO.fromUsuario(usuarioUpdate));
        } else {
            return Optional.empty();
        }
    }

    public boolean delete(Long id) {
        if (UsuarioRepository.existsById(id)) {
            UsuarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

