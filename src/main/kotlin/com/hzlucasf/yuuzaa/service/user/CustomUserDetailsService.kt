package com.hzlucasf.yuuzaa.service.user

import com.hzlucasf.yuuzaa.repository.user.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null) {
            throw IllegalArgumentException("username cannot be null")
        }

        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("username not found")

        return User
            .builder()
            .username(user.username)
            .password(user.password)
            .roles(user.role.name)
            .build()
    }
}
