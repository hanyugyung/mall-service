package org.example.support.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.example.interfaces.CommonResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun configure(): WebSecurityCustomizer? {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().requestMatchers(
                "/api-docs/**",
                "/api-docs",
                "/swagger-ui/**",
                "/favicon.ico",
                "/api/**"
            )
        }
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        return http.authorizeHttpRequests()
            .requestMatchers("/api/delivery").hasRole("ROLE_user")
            .and()
            .httpBasic().disable()
            .formLogin().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests().anyRequest().permitAll()
            .and()
//          TODO auth filter 구현
//            .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling()
            .authenticationEntryPoint { request: HttpServletRequest?, response: HttpServletResponse, authException: AuthenticationException? ->
                response.status = HttpStatus.UNAUTHORIZED.value()
                response.contentType = "application/json"
                CommonResponse.successOf(AccessDeniedException("다시 로그인 해주세요."), HttpStatus.UNAUTHORIZED)
            }
            .accessDeniedHandler { request: HttpServletRequest?, response: HttpServletResponse, accessDeniedException: AccessDeniedException? ->
                response.status = HttpStatus.FORBIDDEN.value()
                response.contentType = "application/json"
                CommonResponse.successOf(AccessDeniedException("다시 로그인 해주세요."), HttpStatus.FORBIDDEN)
            }.and().build()
    }

}