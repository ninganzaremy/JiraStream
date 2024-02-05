package com.remy.jirastream.config;

import com.remy.jirastream.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  private final CustomUserDetailsService customUserDetailsService;
  private final PasswordEncoder passwordEncoder;

  public WebSecurityConfig(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
    this.customUserDetailsService = customUserDetailsService;
    this.passwordEncoder = passwordEncoder;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(customUserDetailsService)
        .passwordEncoder(passwordEncoder);
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        // Authorize requests
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/login**", "/js/**", "/css/**", "/img/**", "/webjars/**")
            .permitAll()  // Allow these paths without
            // authentication
            .requestMatchers("/api/**").authenticated()  // Secure API endpoints
            .anyRequest().permitAll()  // Allow all other requests, including root and index.html, after login
        )
        // Configure form login
        .formLogin(form -> form
//            .loginPage("/login") // Custom login page
                .permitAll()
                .defaultSuccessUrl("/", true) // Redirect to the app root after login
        )
        // Configure logout
        .logout(logout -> logout
            .logoutSuccessUrl("/login?logout") // Redirect to login on logout
            .invalidateHttpSession(true) // Invalidate session on logout
            .clearAuthentication(true) // Clear authentication attributes

            .permitAll()
        )
        // Disable CSRF protection for simplicity in a REST API scenario
        .csrf(AbstractHttpConfigurer::disable // Disabling CSRF

        )
        // Use stateless session; session won't be used to store user's state.
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Session is created if required
        );

    return http.build();
  }

}