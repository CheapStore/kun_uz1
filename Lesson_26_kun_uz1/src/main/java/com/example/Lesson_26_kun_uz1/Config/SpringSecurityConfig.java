package com.example.Lesson_26_kun_uz1.Config;


import com.example.Lesson_26_kun_uz1.Util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import java.util.UUID;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {
    @Autowired
    private CustomUserDetailService detailService;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;


    @Bean
    public AuthenticationProvider authenticationProvider() {
////         authentication
//        String password = UUID.randomUUID().toString();
//        System.out.println("User Pasword mazgi: " + password);
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{noop}" + password)
//                .roles("ROLE_ADMIN")
//                .build();
//
//        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(new InMemoryUserDetailsManager(user));
//        return authenticationProvider;

        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(detailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }


    public static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/article/any/Like/",
            "/article/any/DisLike/",
            "/attach/upload",
            "/auth/login",
            "/auth/login1",
            "/init/admin",
            "/init/**",
            "/region/language",
            "/sms/create",
            "/sms/sms"
    };

    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return MDUtil.encode(rawPassword.toString()).equals(encodedPassword);
            }
        };
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // authorization
        // dostup bor yoqligi
//        http.authorizeHttpRequests()
//                .anyRequest()
//                .authenticated()
//                .and().formLogin();
//        return http.build();

        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            authorizationManagerRequestMatcherRegistry

                    .requestMatchers(AUTH_WHITELIST).permitAll()
                    .requestMatchers("/region/adm/*").hasAnyRole("ADMIN")
                    .requestMatchers("/article/pub/**").hasAnyRole("ADMIN", "PUBLISHER")
                    .requestMatchers("/article/moder","/article/moder/*","/article/moder/**").hasAnyRole("MODERATOR")
                    .requestMatchers("/article/pub/*","/article/pub/**").hasAnyRole("PUBLISHER")
                    .requestMatchers("/profile/adm/*").hasAnyRole("ADMIN")
                    .requestMatchers("/category/adm/*").hasAnyRole("ADMIN")
                    .requestMatchers("/attach/adm/*", "/attach/adm/**").hasAnyRole("ADMIN")
                    .requestMatchers("/types/adm/*", "/attach/adm/**").hasAnyRole("ADMIN")
                    .requestMatchers("/types/mod/*").hasAnyRole("MODERATOR")
                    .requestMatchers("/tag/adm").hasAnyRole("ADMIN")
                    .requestMatchers("/articleComment/adm/*").hasAnyRole("ADMIN")
                    .anyRequest()
                    .authenticated();
        });
//        http.httpBasic(Customizer.withDefaults());
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);

        return http.build();
    }


}
