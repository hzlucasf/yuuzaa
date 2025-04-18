package com.hzlucasf.yuuzaa.controller.sign.`in`

import com.hzlucasf.yuuzaa.service.sign.`in`.SignInService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sign-in")
class SignInController(private val service: SignInService) {
    @PostMapping
    fun signIn(authentication: Authentication) = service.signIn(authentication)
}
