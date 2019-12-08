package com.jpco.app.jonesprintcompany.config;

import com.jpco.app.jonesprintcompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        String[] staticResources  =  {
        		"/2019/**",
        		"/author/**",
        		"/category/**",
        		"/comments/**",
        		"/scss/**",
        		"/vendor/**",
        		"/wp-content/**",
        		"/wp-includes/**",
        		"/localhost/**",
        		"/wp-json/**",
                "/css/**",
                "/images/**",
                "/fonts/**",
                "/js/**",
                "/webjars/**",
            };
        
        String[] templatesResources  =  {
        		"/",        		
        		"/register**",
        		"/registration**",
        		"/index**",
        		"/contact**",
        		"/about-us**",
        		"/password-reset**",
        		"/login-2baf7**",
        		"/login-2**",
        		"/login**",
        		"/pricing**",
        		"/services**",
            };
        
        String[] userRolePages  =  {
        		"/place-order**",
            };
    	
        http
        
//		http
//		.csrf().disable()
//		.authorizeRequests()
//			.antMatchers("/login").permitAll()
//			.antMatchers("/").authenticated()
//		.and()
//		.formLogin()
//		.loginPage("/login").permitAll()
//		.and()
//		.logout().invalidateHttpSession(true)
//		.clearAuthentication(true)
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		.logoutSuccessUrl("/logout-success").permitAll();
        
                .authorizeRequests()
                	.antMatchers(staticResources).permitAll()  
                	.antMatchers(templatesResources).permitAll()
                	.antMatchers(userRolePages).access("hasRole('ROLE_USER')")
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login-2")
                            .permitAll()
                .and()
                    .logout()
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                .permitAll();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

}
