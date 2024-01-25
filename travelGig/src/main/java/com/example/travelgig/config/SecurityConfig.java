package com.example.travelgig.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.httpBasic().and().csrf().disable().authorizeRequests()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/WEB-INF/jsp/**")).permitAll()
                .and()
                .authorizeRequests().requestMatchers("/role").hasAnyAuthority("Admin")
                .and()
                .authorizeRequests().requestMatchers("/").authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/home")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .httpBasic();

        http.userDetailsService(userDetailsService);

        return http.build();
    }
}
