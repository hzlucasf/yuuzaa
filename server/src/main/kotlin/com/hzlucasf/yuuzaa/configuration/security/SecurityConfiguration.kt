package com.hzlucasf.yuuzaa.configuration.security

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.web.SecurityFilterChain
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@Configuration
@EnableWebSecurity
class SecurityConfiguration(private val customAuthenticationEntryPoint: CustomAuthenticationEntryPoint) {
    @Value("\${rsa.key.public}")
    private lateinit var publicKey: RSAPublicKey

    @Value("\${rsa.key.private}")
    private lateinit var privateKey: RSAPrivateKey

    @Bean
    fun getSecurityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain =
        httpSecurity
            .authorizeHttpRequests{
                it
                    .requestMatchers(HttpMethod.POST, "/sign-up")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/sign-in")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/users/**")
                    .hasRole("ADMINISTRATOR")
                    .anyRequest()
                    .denyAll()
            }
            .csrf{
                it.disable()
            }
            .httpBasic(Customizer.withDefaults())
            .oauth2ResourceServer{
                oauth2 -> oauth2.jwt { it.jwtAuthenticationConverter(getJwtAuthenticationConverter()) }
            }
            .exceptionHandling{
                it.authenticationEntryPoint(customAuthenticationEntryPoint)
            }
            .cors(Customizer.withDefaults())
            .build()

    @Bean
    fun getPasswordEncoder(): PasswordEncoder = Argon2PasswordEncoder(16, 32, 1, (1 shl 14), 2)

    @Bean
    fun getJwtEncoder(): JwtEncoder {
        val jwk = RSAKey.Builder(publicKey).privateKey(privateKey).build()

        val jwkSet = ImmutableJWKSet<SecurityContext>(JWKSet(jwk))

        return NimbusJwtEncoder(jwkSet)
    }

    @Bean
    fun getJwtDecoder(): JwtDecoder = NimbusJwtDecoder.withPublicKey(publicKey).build()

    fun getJwtAuthenticationConverter(): JwtAuthenticationConverter {
        val jwtAuthenticationConverter = JwtAuthenticationConverter()

        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter {
            jwt -> jwt.getClaimAsStringList("roles").map { SimpleGrantedAuthority(it.uppercase()) }
        }

        return jwtAuthenticationConverter
    }
}
