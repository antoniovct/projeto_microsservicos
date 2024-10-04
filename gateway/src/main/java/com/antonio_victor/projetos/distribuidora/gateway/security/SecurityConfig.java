package com.antonio_victor.projetos.distribuidora.gateway.security;

import com.antonio_victor.projetos.distribuidora.gateway.service.AuthService;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Flux;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.stream.Collectors;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Value("${jwt.public.key}")
    private RSAPublicKey key;
    @Value("${jwt.private.key}")
    private RSAPrivateKey priv;


    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity http, ReactiveAuthenticationManager authenticationManager) throws Exception {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(auth -> auth
                        .pathMatchers("/usuarios/**").permitAll()
                        .pathMatchers("/authenticate").permitAll()
                        .pathMatchers(HttpMethod.GET,"/estoque-ms/estoque/produtos/**").hasAnyRole("ADMIN", "SELLER")
                        .pathMatchers("/estoque-ms/estoque/produtos/cadastro").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.PUT,"/estoque-ms/estoque/produtos/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE,"/estoque-ms/estoque/produtos/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.GET,"/estoque-ms/estoque/marcas/**").hasAnyRole("ADMIN", "SELLER")
                        .pathMatchers("/estoque-ms/estoque/marcas/cadastrar").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.PUT,"/estoque-ms/estoque/marcas/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE,"/estoque-ms/estoque/marcas/**").hasRole("ADMIN")
                        .pathMatchers("/cadastros-ms/vendedores/**").hasRole("ADMIN")
                        .pathMatchers("/cadastros-ms/clientes/**").hasAnyRole("ADMIN", "SELLER")
                        .pathMatchers("/vendas-ms/pedidos/**").hasAnyRole("ADMIN", "SELLER")
                        .anyExchange().authenticated())
                .oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()))
                .authenticationManager(authenticationManager)
                .build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return NimbusReactiveJwtDecoder.withPublicKey(key).build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        var jwk = new RSAKey.Builder(key).privateKey(priv).build();
        var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(AuthService authService) {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(authService);
        authenticationManager.setPasswordEncoder(passwordEncoder());
        return authenticationManager;
    }

    @Bean
    public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
        ReactiveJwtAuthenticationConverter converter = new ReactiveJwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            var roles = jwt.getClaimAsStringList("roles");
            return Flux.fromIterable(
                    roles.stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList())
            );
        });
        return converter;
    }


}
