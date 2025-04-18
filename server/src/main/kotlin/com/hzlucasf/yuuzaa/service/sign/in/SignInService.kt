package com.hzlucasf.yuuzaa.service.sign.`in`

import com.hzlucasf.yuuzaa.service.jwt.JwtService
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class SignInService(private val jwtService: JwtService) {
    fun signIn(authentication: Authentication) = jwtService.generateJwt(authentication)
}
