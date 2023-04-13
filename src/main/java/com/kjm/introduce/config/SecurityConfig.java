package com.kjm.introduce.config;

import com.kjm.introduce.config.handler.LoginFailureHandler;
import com.kjm.introduce.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * SecurityConfig.java
 * Class 설명을 작성하세요.
 *
 * @author kjm
 * @since 2023.04.06
 */
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberRepository memberRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(memberRepository);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new LoginFailureHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .formLogin()
                .loginPage("/members/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .failureHandler(authenticationFailureHandler());

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(false);

        http.rememberMe()
                .rememberMeParameter("remember-me")
                .userDetailsService(userDetailsService());

        http.authorizeRequests()
                .mvcMatchers("/", "/members/login", "/members/login/error", "/members/sign-up").permitAll()
                .antMatchers("/css/**", "/img/**", "/js/**", "/vendor/**", "/scss/**").permitAll()
                .antMatchers("/detail/**").hasAnyRole("SILVER","GOLD")
                .anyRequest().authenticated();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
