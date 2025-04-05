package com.hzlucasf.yuuzaa.controller.sign.up

import com.hzlucasf.yuuzaa.model.user.request.UserRequest
import com.hzlucasf.yuuzaa.service.sign.up.SignUpService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sign-up")
class SignUpController(private val service: SignUpService) {
    @PostMapping
    fun signUp(@RequestBody @Valid userRequest: UserRequest) =
        ResponseEntity.status(HttpStatus.CREATED).body(service.signUp(userRequest))
}
