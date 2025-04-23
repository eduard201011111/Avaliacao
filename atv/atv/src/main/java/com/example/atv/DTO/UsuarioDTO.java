package com.example.atv.DTO;

import com.example.atv.Entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioDTO implements Serializable{

    private Long id;
    private String nome;
    private String sobrenome;
    private Double cpf;
    private String email;
    private String username;
    private int senha;
    private LocalDate dataNascimento;

public Usuario toUsuario() {
        return new Usuario(
                this.id,
                this.nome,
                this.sobrenome,
                this.cpf,
                this.email,
                this.username,
                this.senha,
                this.dataNascimento
        );
    }
    public UsuarioDTO fromUsuario(Usuario usuario){
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.getSenha(),
                usuario.getDataNascimento()
        );
    }
}











