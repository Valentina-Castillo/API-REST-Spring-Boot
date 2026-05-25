package com.ipartek.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ipartek.componente.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                        // Forwards internos de Thymeleaf/Spring MVC
                        .dispatcherTypeMatchers(
                                jakarta.servlet.DispatcherType.FORWARD,
                                jakarta.servlet.DispatcherType.ERROR).permitAll()

                        // Páginas web (Thymeleaf)
                        .requestMatchers("/", "/login", "/dashboard", "/marcas", "/coches", "/error").permitAll()

                        // Recursos estáticos (CSS, JS, imágenes)
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/favicon.ico").permitAll()
                        .requestMatchers("/styles/**", "/imagenes/**").permitAll()

                        // Swagger / OpenAPI
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/api-docs/**"
                        ).permitAll()

                        // Autenticación
                        .requestMatchers(HttpMethod.POST, "/api/v1/usuarios/ValidarUsuario").permitAll()

                        // API abierta de marcas y coches
                        .requestMatchers("/api/v1/marcas/**").permitAll()
                        .requestMatchers("/api/v1/coches/**").permitAll()

                        // Endpoints de usuarios protegidos
                        .requestMatchers(HttpMethod.PUT,    "/api/v1/usuarios/bloquear/").hasAnyRole("SYSTEM")
                        .requestMatchers(HttpMethod.POST,   "/api/v1/usuarios/").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,    "/api/v1/usuarios/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/usuarios/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,    "/api/v1/usuarios/**").hasAnyRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
