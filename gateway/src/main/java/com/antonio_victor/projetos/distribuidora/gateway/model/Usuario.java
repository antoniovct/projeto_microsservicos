package com.antonio_victor.projetos.distribuidora.gateway.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Table(name = "usuarios")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @Setter(AccessLevel.NONE)
    private Long id;
    private String email;
    private String senha;

    public Usuario(DtoUsuario dtoUsuario, String senha) {
        this.email = dtoUsuario.email();
        this.senha = senha;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.email.contains("@admin.com.br")) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (this.email.contains("@seller.com.br")) {
            return List.of(new SimpleGrantedAuthority("ROLE_SELLER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
