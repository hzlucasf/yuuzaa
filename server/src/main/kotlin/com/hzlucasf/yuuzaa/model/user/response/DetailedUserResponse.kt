package com.hzlucasf.yuuzaa.model.user.response

import com.hzlucasf.yuuzaa.model.role.Role

data class DetailedUserResponse(val id: String, val username: String, val role: Role)
