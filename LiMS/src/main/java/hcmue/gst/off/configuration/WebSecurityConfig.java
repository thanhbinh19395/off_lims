package hcmue.gst.off.configuration;

import hcmue.gst.off.authentication.RESTAuthenticationSuccessHandler;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.repositories.UserRepository;
import hcmue.gst.off.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by WIN8.1 on 09/02/2017.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


/*    @Autowired
    private UserDetailsService userDetailsService;
*/

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
                .antMatchers("/Admin/**", "/Librarian/**", "/User/**").authenticated()
                .antMatchers("/**", "/css/**").permitAll()
                .antMatchers("/Admin/**").hasRole("ADMIN")
                .antMatchers("/Librarian/**").hasRole("ADMIN")
                .antMatchers("/Librarian/**").hasRole("LIBRARIAN")
                .antMatchers("/User/**").hasRole("USER");
        http
                .formLogin()
                .loginPage("/login")
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

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inMemoryUserDetailsManager());
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        Properties users = new Properties();
        for(User user: userRepository.findAll())
        users.put(user.getUsername(), user.getPassword()+","+user.getRole().getName()+",enabled");
        return new InMemoryUserDetailsManager(users);
    }

}
