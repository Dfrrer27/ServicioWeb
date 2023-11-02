package com.jcastaneda.servicioweb

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostApiService {
    @GET("api/usuarios")
    suspend fun getUserPost():ArrayList<PostModelResponse>

    @GET("api/usuario/{id}")
    suspend fun getUserPostById(@Path("id") id:String):ArrayList<PostModelResponse>

    @POST("api/login") // Ruta al endpoint de inicio de sesión en tu API
    fun login(@Body loginData: LoginData): Call<PostModelResponse>
}