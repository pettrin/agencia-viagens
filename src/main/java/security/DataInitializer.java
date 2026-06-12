package com.agencia.viagens.security;

import com.agencia.viagens.model.Perfil;
import com.agencia.viagens.model.Usuario;
import com.agencia.viagens.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner criarUsuariosIniciais(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {
            if (!usuarioRepository.existsByEmail("admin@viagens.com")) {
                Usuario admin = new Usuario(
                        null,
                        "Administrador",
                        "admin@viagens.com",
                        passwordEncoder.encode("123456"),
                        Perfil.ADMIN
                );

                usuarioRepository.save(admin);
            }

            if (!usuarioRepository.existsByEmail("cliente@viagens.com")) {
                Usuario cliente = new Usuario(
                        null,
                        "Cliente",
                        "cliente@viagens.com",
                        passwordEncoder.encode("123456"),
                        Perfil.CLIENTE
                );

                usuarioRepository.save(cliente);
            }
        };
    }
}