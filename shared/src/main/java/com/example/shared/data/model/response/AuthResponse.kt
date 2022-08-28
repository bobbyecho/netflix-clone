package com.example.shared.data.model.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("token")
    val token: String?,
    @SerializedName("token_type")
    val token_type: String?,
    @SerializedName("user")
    val user: UserResponse?,
)