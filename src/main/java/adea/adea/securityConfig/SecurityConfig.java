package adea.adea.securityConfig;

import adea.adea.service.ShaBase64PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    @Bean    
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.antMatchers("/adea/**").authenticated()
                )
                .formLogin(form -> form
                .defaultSuccessUrl("/adea", true)
                )
                .build();                
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new ShaBase64PasswordEncoder();
    }
}
