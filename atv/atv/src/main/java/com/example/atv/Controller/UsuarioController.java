package com.example.atv.Controller;

import com.example.atv.DTO.UsuarioDTO;
import com.example.atv.Entity.Usuario;
import com.example.atv.Service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")

public class UsuarioController {

        @Autowired
        private UsuarioService usuarioService;

        @GetMapping
        public List<Usuario> getAll(){
            return usuarioService.getAllUsuarios();
        }

        @GetMapping("/{id}")
        public ResponseEntity<UsuarioDTO> getById(@PathVariable Long id){
            Optional<UsuarioDTO> UsuarioDTOOptional = usuarioService.getById(id);
            return UsuarioDTOOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @PostMapping
        public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO usuarioDTO){
            UsuarioDTO usuarioDTOSave = usuarioService.createUsuario(usuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTOSave);
        }

        @PutMapping("/{id}")
        public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
            Optional<UsuarioDTO> usuarioDTOOptional = usuarioService.updateUsuario(id, usuarioDTO);
            return usuarioDTOOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id){
            if(usuarioService.delete(id)){
                return ResponseEntity.noContent().build();
            }else {
                return ResponseEntity.notFound().build();
            }
        }
    }
