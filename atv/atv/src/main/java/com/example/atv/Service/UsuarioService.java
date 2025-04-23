
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
            Usuario Usuario = usuarioOptional.get();
            Usuario.setId(usuarioDTO.getId());
            Usuario.setNome(usuarioDTO.getNome());
            Usuario.setSobrenome(usuarioDTO.getSobrenome());
            Usuario.setCpf(usuarioDTO.getCpf());
            Usuario.setEmail(usuarioDTO.getEmail());
            Usuario.setUsername(usuarioDTO.getUsername());
            Usuario.setSenha(usuarioDTO.getSenha());
            Usuario.setDataNascimento(usuarioDTO.getDataNascimento());

            Usuario usuarioUpdate = UsuarioRepository.save(Usuario);

            return Optional.of(usuarioDTO.fromUsuario(Usuario));
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

