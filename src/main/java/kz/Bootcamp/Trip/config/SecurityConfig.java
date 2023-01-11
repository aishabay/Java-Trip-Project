package kz.Bootcamp.Trip.config;

import kz.Bootcamp.Trip.service.UserService;
import kz.Bootcamp.Trip.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true, securedEnabled = true)
public class SecurityConfig {

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService()).passwordEncoder(passwordEncoder());

        http.authorizeRequests().antMatchers("/css/**","/js/**").permitAll();

        http.formLogin()
                .loginProcessingUrl("/auth").permitAll()
                .defaultSuccessUrl("/profile")
                .failureUrl("/signin?error")
                .loginPage("/signin").permitAll()
                .usernameParameter("user_email")
                .passwordParameter("user_password");

        http.logout()
                .logoutSuccessUrl("/signin")
                .logoutUrl("/signout");

        http.csrf().disable();

        return http.build();
    }
}
