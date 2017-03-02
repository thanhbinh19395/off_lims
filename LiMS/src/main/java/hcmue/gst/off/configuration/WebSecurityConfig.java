package hcmue.gst.off.configuration;

import hcmue.gst.off.authentication.RESTAuthenticationSuccessHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.sql.DataSource;

/**
 * Created by WIN8.1 on 09/02/2017.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private RESTAuthenticationSuccessHandler authenticationSuccessHandler;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    private  UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/Admin/**").hasAuthority("ADMIN")
                .antMatchers("/Librarian/**").hasAnyAuthority("LIBRARIAN","ADMIN")
                .antMatchers("/User/**").hasAnyAuthority("USER","ADMIN","LIBRARIAN");
        http
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .failureUrl("/login?error")
                .successHandler(authenticationSuccessHandler);
        http
                .logout()
                .permitAll();
        http
                .csrf().disable();
        http
                .exceptionHandling().accessDeniedPage("/Access_Denied");
    }

}
