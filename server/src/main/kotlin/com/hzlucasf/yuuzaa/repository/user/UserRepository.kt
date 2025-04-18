package com.hzlucasf.yuuzaa.repository.user

import com.hzlucasf.yuuzaa.model.user.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User, String> {
    fun findByUsername(username: String): User?

    fun existsByUsername(username: String): Boolean
}
