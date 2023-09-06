package com.example.restapi.data
import retrofit2.http.GET
interface RabbitsAPI {
    @GET("/rabbit")
    suspend fun getRandomRabbit(): Rabbit

    companion object {
        const val BASE_URL = "http://10.0.0.231:8080"
    }
}