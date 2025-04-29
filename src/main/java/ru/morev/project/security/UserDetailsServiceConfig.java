package ru.morev.project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserDetailsServiceConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin")
                        .password(encoder.encode("adminpass"))
                        .roles("ADMIN")
                        .build(),
                User.withUsername("support")
                        .password(encoder.encode("supportpass"))
                        .roles("SUPPORT")
                        .build(),
                User.withUsername("user")
                        .password(encoder.encode("userpass"))
                        .roles("USER")
                        .build()
        );
    }
}
