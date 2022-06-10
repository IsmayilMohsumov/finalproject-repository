package com.example.finalproject.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

//    @Autowired
//    private  CustomLoginSuccessHandler successHandler;

    @Value("${spring.queries.users-query}")
    private String usersQuery; //olub olmafigini qaytarir

    @Value("${spring.queries.roles-query}")
    private String rolesQuery; // hansi roldadir onu qaytarir



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                // URLs matching for access rights
                .antMatchers("/","/login","/register").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/signin").permitAll()
                .antMatchers("/verify/**").permitAll()
                .antMatchers("/show-questions/**").permitAll()
                .antMatchers("/test/**").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN_USER")
                .antMatchers("/admin/**").permitAll()
                .antMatchers("/user/**").hasAuthority( "SITE_USER")
                .anyRequest().authenticated()
                .and()
                // form login
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/user",true)
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                // logout
                .logout()
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/")
                .permitAll();

    }


    @Override
    public void configure(WebSecurity web) throws Exception {
             web
                .ignoring()
                    .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/Image/**", "/scss/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                        .passwordEncoder(new BCryptPasswordEncoder())
                                .dataSource(dataSource)
                                        .usersByUsernameQuery(usersQuery)
                                                .authoritiesByUsernameQuery(rolesQuery);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
