package com.hzlucasf.yuuzaa.model.user.request

import jakarta.validation.constraints.NotBlank

data class UserRequest(@NotBlank val username: String = "", @NotBlank val password: String = "")
