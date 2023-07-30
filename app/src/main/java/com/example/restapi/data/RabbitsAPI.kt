package com.example.restapi.data
import retrofit2.http.GET
interface RabbitsAPI {
    @GET("/r")
    suspend fun getRandomRabbit(): Rabbit

    companion object {
        const val BASE_URL = "https://10.0.2.2:8080"
    }
}