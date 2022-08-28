package com.example.shared.repository

import com.example.core.base.BaseRepository
import com.google.gson.Gson
import com.google.gson.JsonParseException
import okhttp3.ResponseBody

class Repository : BaseRepository() {
    private val gson: Gson by inject(Gson::class.java)

    override fun <T> getErrorMessageFromApi(response: T): String {
        val responseBody = response as ResponseBody
        return try {
            val body = gson.fromJson(responseBody.string(), BaseRepository::class.java)
            body.message ?: "Error Api"
        } catch (e: JsonParseException) {
            "Error Api"
        }

    }
}
