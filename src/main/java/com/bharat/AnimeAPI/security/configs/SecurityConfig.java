package com.bharat.AnimeAPI.security.configs;

import com.bharat.AnimeAPI.security.models.User;
import com.bharat.AnimeAPI.security.models.UserSaveWrapper;
import com.bharat.AnimeAPI.security.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();
        corsConfigSource.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());

        http
                .cors(
                        corsConfig -> corsConfig.configurationSource(corsConfigSource)
                )
                .csrf().disable()
                .authorizeHttpRequests(
                        registry -> {
                            registry.requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                                    .requestMatchers(HttpMethod.GET, "/").permitAll()
                                    .anyRequest().authenticated();
                        }
                )
                .httpBasic(withDefaults())
                .formLogin();

        return http.build();
    }

    @Bean
    public UserDetailsService userService(UserRepository userRepository){
        return username -> {
            UserSaveWrapper dummyUser = UserSaveWrapper.builder()
                    .username("Not found")
                    .password("Not found")
                    .email("Not found")
                    .build();

            UserSaveWrapper userSaved = userRepository.findById(username).orElse(dummyUser);

            return User.builder()
                    .username(userSaved.getUsername())
                    .password(encoder().encode(userSaved.getPassword()))
                    .email(userSaved.getEmail())
                    .build();
        };
    }
}