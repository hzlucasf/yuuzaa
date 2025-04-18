package com.hzlucasf.yuuzaa.model.user

import com.hzlucasf.yuuzaa.model.role.Role
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "user")
data class User(
    @Id val id: String = UUID.randomUUID().toString(),
    val username: String = "",
    val password: String = "",
    val role: Role = Role.USER
)
