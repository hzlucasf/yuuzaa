package com.hzlucasf.yuuzaa.service.jwt

import com.hzlucasf.yuuzaa.model.jwt.response.JwtResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class JwtService(private val encoder: JwtEncoder) {
    @Value("\${spring.project.name}")
    private lateinit var issuer: String

    fun generateJwt(authentication: Authentication): JwtResponse {
        val issuedAt = Instant.now()

        val expiresAt = issuedAt.plusSeconds(60 * 60)

        val claims = JwtClaimsSet
            .builder()
            .issuer(issuer)
            .issuedAt(issuedAt)
            .expiresAt(expiresAt)
            .subject(authentication.name)
            .claim("roles", authentication.authorities.map { it.authority }.toList())
            .build()

        return JwtResponse(encoder.encode(JwtEncoderParameters.from(claims)).tokenValue)
    }
}
