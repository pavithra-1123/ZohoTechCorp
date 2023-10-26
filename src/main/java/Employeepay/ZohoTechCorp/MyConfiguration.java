package Employeepay.ZohoTechCorp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Stack;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
public class MyConfiguration
{
    @Autowired
    EmployeeDetailsService serv;

    AuthenticationManager manager;
    @Bean
    public WebSecurityCustomizer customizer()
    {
        return (web ) -> web.ignoring().requestMatchers("/ZohoTech/create");
    }
    @Bean
    public PasswordEncoder crpter()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailService()
    {
        UserDetails emp1 = User.withUsername("pavithra").password(crpter().encode("password1")).roles("USER").build();
        UserDetails emp2= User.withUsername("Thara").password(crpter().encode("thara12")).roles("CUSTOMER").build();
        Collection<UserDetails> myusers = Stream.of(emp1,emp2).toList();
        return  new InMemoryUserDetailsManager(myusers);
    }
    @Bean
    @Deprecated(since = "6.1", forRemoval = true)
    public SecurityFilterChain ZohoSecure(HttpSecurity hp) throws Exception
    {
//        hp.authorizeHttpRequests().
//                requestMatchers("/ZohoTech").
////                authenticated();
//                hp.authorizeHttpRequests().
//                anyRequest().permitAll();//Servers is not maintained with user,pass
//        hp.authorizeHttpRequests().
//                anyRequest().authenticated();//Secure all servers

        hp.authorizeRequests().
                requestMatchers("/ZohoTech/**").authenticated();
        hp.csrf().disable();
        hp.cors();
        hp.httpBasic();
        hp.formLogin();

        AuthenticationManagerBuilder builder = hp.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(serv).passwordEncoder(crpter());

        manager= builder.build();
        hp.authenticationManager(manager);

        return hp.build();
    }
    }
