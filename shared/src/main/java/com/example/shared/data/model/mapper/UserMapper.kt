package com.example.shared.data.model.mapper

import com.example.shared.data.model.response.UserResponse
import com.example.shared.data.model.viewParam.UserViewParam
import com.example.shared.utils.Mapper
import com.google.gson.annotations.SerializedName

object UserMapper: Mapper.ViewParamMapper<UserResponse, UserViewParam> {
    override fun toViewParam(dataObject: UserResponse?): UserViewParam = UserViewParam(
        email = dataObject?.email.orEmpty(),
        birthdate = dataObject?.birthdate.orEmpty(),
        gender = dataObject?.gender ?: -1,
        id = dataObject?.id ?: -1,
        username = dataObject?.username.orEmpty()
    )
}