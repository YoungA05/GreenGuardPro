package com.greenguardpro.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.greenguardpro.mvc.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserService();
	}
	
	
	/*@Bean
	public AuthenticationManager authenticationManager(UserService userservice,PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userservice);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}*/
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return authenticationProvider;
	}

	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login","/Register").permitAll()
                .anyRequest().authenticated()
            ).httpBasic(Customizer.withDefaults())
            
            .formLogin(formLogin -> formLogin
                .loginPage("/login")
                .defaultSuccessUrl("/index")
                .successHandler((request, response, authentification) -> {response.sendRedirect("/index");})
                
                .permitAll()
                
            ).logout((logout)-> logout.deleteCookies("remove")
            		.invalidateHttpSession(false)
            		.logoutUrl("/logout")
            		.logoutSuccessUrl("/login?logout").permitAll())
            
            .requiresChannel((requiresChannel) ->
				requiresChannel
					.anyRequest().requiresSecure())
            
            ;
            

        return http.build();
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}