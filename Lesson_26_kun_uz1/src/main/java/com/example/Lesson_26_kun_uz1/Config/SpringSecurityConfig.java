package com.example.Lesson_26_kun_uz1.Config;

import com.example.Lesson_26_kun_uz1.Util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;

import java.util.UUID;

//@Configuration
//public class SpringSecurityConfig {
//    @Autowired
//    private CustomUserDetailService detailService;
//    @Bean
//
//    public AuthenticationProvider authenticationProvider() {
//        // authentication
////        String password = UUID.randomUUID().toString();
////        System.out.println("User Pasword mazgi: " + password);
////
////        UserDetails user = User.builder()
////                .username("user")
////                .password("{noop}" + password)
////                .roles("ADMIN")
////                .build();
////
////        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
////        authenticationProvider.setUserDetailsService(new InMemoryUserDetailsManager(user));
////        return authenticationProvider;
//
//        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(detailService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//
//    }
//
//    public PasswordEncoder passwordEncoder() {
//        return new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//                return rawPassword.toString();
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                return MDUtil.encode(rawPassword.toString()).equals(encodedPassword);
//            }
//        };
//    }
//
//
//        @Bean
//        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////            // authorization
////            // dostup bor yoqligi
////            http.authorizeHttpRequests()
////                    .anyRequest()
////                    .authenticated()
////                    .and().formLogin();
////            return http.build();
//
//            http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
//                authorizationManagerRequestMatcherRegistry
//                        .requestMatchers("/auth/**").permitAll()
//                        .requestMatchers("/init/admin").permitAll()
//                        .requestMatchers("/init/*").permitAll()
//                        .requestMatchers("/region/language").permitAll()
//                        .requestMatchers("/region/adm/**").hasAnyRole("ADMIN")
//                        .requestMatchers("/article/pub/**").hasAnyRole("ADMIN","PUBLISHER")
//                        .requestMatchers("/profile/adm","/profile/adm/**").hasAnyRole("ADMIN")
//                        .anyRequest()
//                        .authenticated();
//            });
//            http.httpBasic(Customizer.withDefaults());
//            http.csrf(AbstractHttpConfigurer::disable);
//            http.cors(AbstractHttpConfigurer::disable);
//
//            return http.build();
//        }
//
//
//
//}
