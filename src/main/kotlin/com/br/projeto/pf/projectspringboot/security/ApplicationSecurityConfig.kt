package com.br.projeto.pf.projectspringboot.security

import com.br.projeto.pf.projectspringboot.enum.UserPermission
import com.br.projeto.pf.projectspringboot.enum.UserRole
import com.br.projeto.pf.projectspringboot.service.UserService
import com.br.projeto.pf.projectspringboot.util.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class ApplicationSecurityConfig(
    private val passwordEncoder: BCryptPasswordEncoder,
    private val userService: UserService,
    ) : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.DELETE, PATH_ACCOUNT).hasAuthority(UserPermission.ADMIN_WRITE.permission)
            .antMatchers(HttpMethod.GET, PATH_ACCOUNT).hasAnyRole(UserRole.NORMAL.name, UserRole.ADMIN.name)
            .antMatchers(HttpMethod.DELETE, PATH_USER).hasAuthority(UserPermission.ADMIN_WRITE.permission)
            .antMatchers(HttpMethod.GET, PATH_USER).hasAnyRole(UserRole.NORMAL.name, UserRole.ADMIN.name)
            .antMatchers(HttpMethod.DELETE, PATH_COMPANY).hasAuthority(UserPermission.ADMIN_WRITE.permission)
            .antMatchers(HttpMethod.GET, PATH_COMPANY).hasAnyRole(UserRole.NORMAL.name, UserRole.ADMIN.name)
            .antMatchers(PATH_HOME_ALL).hasAnyRole(UserRole.NORMAL.name, UserRole.ADMIN.name)
            .antMatchers(PATH_H2).permitAll()
            .antMatchers(PATH_LOGIN).permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic().disable()
            .formLogin()
            .loginPage(PATH_LOGIN)
            .defaultSuccessUrl(PATH_HOME, true)
            .and()
            .logout()
            .deleteCookies("JSESSIONID")
            .and()
            .headers().frameOptions().sameOrigin()
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder){
        auth.authenticationProvider(authenticationProvider())
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setPasswordEncoder(passwordEncoder)
        authenticationProvider.setUserDetailsService(userService)
        return authenticationProvider
    }
}