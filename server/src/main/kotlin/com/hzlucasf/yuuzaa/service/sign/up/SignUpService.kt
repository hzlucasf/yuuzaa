package com.hzlucasf.yuuzaa.service.sign.up

import com.hzlucasf.yuuzaa.exception.user.InvalidPasswordException
import com.hzlucasf.yuuzaa.exception.user.InvalidUsernameException
import com.hzlucasf.yuuzaa.exception.user.UserAlreadyRegisteredException
import com.hzlucasf.yuuzaa.model.user.User
import com.hzlucasf.yuuzaa.model.user.request.UserRequest
import com.hzlucasf.yuuzaa.model.user.response.UserResponse
import com.hzlucasf.yuuzaa.repository.user.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SignUpService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {
    private val usernameRegex = Regex.fromLiteral("^[a-zA-Z_]+\$")

    fun signUp(userRequest: UserRequest): UserResponse {
        if (userRequest.username.isBlank()) {
            throw InvalidUsernameException("the username cannot be blank")
        }

        if (userRequest.username.length > 16) {
            throw InvalidUsernameException("the username cannot be longer than 16 characters")
        }

        if (userRequest.username.matches(usernameRegex)) {
            throw InvalidUsernameException("the username must only contain letters and underscores")
        }

        if (userRequest.password.length < 8) {
            throw InvalidPasswordException("the password must be at least 8 characters long")
        }

        if (userRequest.password.length > 64) {
            throw InvalidPasswordException("the password cannot be longer than 64 characters")
        }

        if (userRepository.existsByUsername(userRequest.username)) {
            throw UserAlreadyRegisteredException("user already registered")
        }

        val user = User(
            username = userRequest.username,
            password = passwordEncoder.encode(userRequest.password)
        )

        userRepository.save(user)

        return UserResponse(id = user.id, username = user.username)
    }
}
