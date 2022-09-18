package com.example.shared.data.model.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<D>(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("success")
    val isSuccess: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: D?,
)