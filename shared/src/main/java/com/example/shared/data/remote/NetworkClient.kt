package com.example.shared.data.remote

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.shared.domain.GetUserTokenUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkClient(
    val getTokenUseCase: GetUserTokenUseCase,
    val chuckerInterceptor: ChuckerInterceptor
) {
    inline fun <reified I> create(): I {
        val authInterceptor = Interceptor {
            val requestBuilder = it
                .request()
                .newBuilder()
            runBlocking {
                getTokenUseCase().first { tokenRespose ->
                    val token = tokenRespose.payload
                    if (!token.isNullOrEmpty()) {
                        requestBuilder.addHeader("Authorization", "Bearer ${token}")
                    }
                    true
                }
            }
            it.proceed(requestBuilder.build())
        }

        // okhttp
        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(chuckerInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()

        // retrofit
        val retrofitClient = Retrofit
            .Builder()
//            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofitClient.create(I::class.java)
    }
}