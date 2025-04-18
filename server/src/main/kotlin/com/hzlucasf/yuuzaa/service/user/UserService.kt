package com.hzlucasf.yuuzaa.service.user

import com.hzlucasf.yuuzaa.exception.user.UserNotFoundException
import com.hzlucasf.yuuzaa.model.user.response.DetailedUserResponse
import com.hzlucasf.yuuzaa.repository.user.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val repository: UserRepository) {
    fun findAll() = repository.findAll().map {
        DetailedUserResponse(it.id, it.username, it.role)
    }.toList()

    fun findById(id: UUID): DetailedUserResponse {
        val optionalUser = repository.findById(id.toString())

        if (optionalUser.isEmpty) {
            throw UserNotFoundException("user not found")
        }

        val user = optionalUser.get()

        return DetailedUserResponse(user.id, user.username, user.role)
    }
}
