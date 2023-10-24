package com.group2.secotool_app.configuration.security;

import com.group2.secotool_app.model.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtRequestFilter jwtRequestFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(authorize ->
                    authorize
                            .requestMatchers("/v1/api/products/open/**",
                                    "/v1/api/rentals/validate",
                                    "/v1/api/categories/open/**",
                                    "/v1/api/features/open/**",
                                    "/v1/api/politics/open/**",
                                    "/v1/api/auth/**",
                                    "/v2/api-docs",
                                    "/v3/api-docs",
                                    "/v3/api-docs/**",
                                    "/swagger-resources",
                                    "/swagger-resources/**",
                                    "/configuration/ui",
                                    "/configuration/security",
                                    "/swagger-ui/**",
                                    "/webjars/**",
                                    "/swagger-ui.html",
                                    "/*.html",
                                    "/css/**",
                                    "/assets/**",
                                    "/scripts/**",
                                    "/*.js").permitAll()
                            //products
                            .requestMatchers("/v1/api/products/admin/**").hasAuthority(UserRole.ADMIN.name())

                            //users
                            .requestMatchers("/v1/api/users/products/**","/v1/api/users/update/**").hasAnyAuthority(UserRole.ADMIN.name(),UserRole.USER.name())
                            .requestMatchers("/v1/api/users/admin/**").hasAuthority(UserRole.ADMIN.name())

                            //rentals
                            .requestMatchers("/v1/api/rentals/**").hasAnyAuthority(UserRole.ADMIN.name(),UserRole.USER.name())
                            .requestMatchers("/v1/api/rentals/admin/**").hasAuthority(UserRole.ADMIN.name())

                            //features
                            .requestMatchers("/v1/api/features/admin/**").hasAuthority(UserRole.ADMIN.name())

                            //categories
                            .requestMatchers("/v1/api/categories/admin/**").hasAuthority(UserRole.ADMIN.name())

                            //politics
                            .requestMatchers("/v1/api/politics/admin/**").hasAuthority(UserRole.ADMIN.name())

                            //reviews
                            .requestMatchers("/v1/api/reviews/**").hasAnyAuthority(UserRole.USER.name(),UserRole.ADMIN.name())
                            .anyRequest().authenticated()
                )
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session ->
                        session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .csrf(crsf ->
                        crsf.disable()
                )
                .logout(log ->
                        log.
                                logoutUrl("/v1/api/auth/logout").
                                logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // "http://0823grupo2proyectointegrador.s3-website-us-east-1.amazonaws.com/"
        configuration.addAllowedOriginPattern("*");
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH","PUT","DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}