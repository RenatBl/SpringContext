package ru.itis.context.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier(value = "customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/signUp", "/signIn").permitAll()
                .antMatchers("/users").hasAuthority("ADMIN")
                .antMatchers("/user").authenticated()
                .antMatchers("/posts").authenticated()
                .antMatchers("/").authenticated()
            .and()
                .formLogin()
                    .loginPage("/signIn")
                    .defaultSuccessUrl("/")
                    .failureUrl("/signIn?error")
                    .usernameParameter("userName")
                    .passwordParameter("password")
                    .permitAll()
                .and()
                    .logout().logoutSuccessUrl("/signIn")
                    .permitAll();
    }
}
