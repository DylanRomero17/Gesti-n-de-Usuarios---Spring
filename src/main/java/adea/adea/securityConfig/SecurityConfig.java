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
public class SecurityConfig {
    
    @Bean    
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/adea/registro", "/login", "/adea/registrar").permitAll()
                        .antMatchers("/adea/**").authenticated()
                )
                .formLogin(form -> form
                       .loginPage("/adea/login").permitAll()
                .defaultSuccessUrl("/adea", true)
                )
                .build();                
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new ShaBase64PasswordEncoder();
    }
}
