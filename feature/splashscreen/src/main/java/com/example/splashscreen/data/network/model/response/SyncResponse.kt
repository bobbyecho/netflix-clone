package com.example.splashscreen.data.network.model.response

import com.example.shared.data.model.response.UserResponse
import com.google.gson.annotations.SerializedName

data class SyncResponse (
    @SerializedName("user")
    val userResponse: UserResponse? = null
)