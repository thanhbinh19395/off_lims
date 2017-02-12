package hcmue.gst.off.configuration;

import hcmue.gst.off.authentication.RESTAuthenticationSuccessHandler;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by WIN8.1 on 09/02/2017.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private RESTAuthenticationSuccessHandler authenticationSuccessHandler;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/Hello/**").authenticated()
                .antMatchers("/**", "/css/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/librarian/**").hasRole("LIBRARIAN")
                .antMatchers("/user/**").hasRole("USER");
        http
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .successHandler(authenticationSuccessHandler);
        http
                .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        for (User user : userRepository.findAll()) {
            auth.inMemoryAuthentication().withUser(user.getUsername()).password(user.getPassword()).roles("USER");
        }
    }

}
