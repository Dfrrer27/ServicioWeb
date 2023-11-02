package com.jcastaneda.servicioweb

import com.google.gson.annotations.SerializedName

data class PostModelResponse (
    @SerializedName("id")
    var id: Int,
    @SerializedName("usuario")
    var usuario:String,
    @SerializedName("email")
    var email:String,
    @SerializedName("password")
    var password:String
)

data class LoginData(
    val email: String,
    val password: String
)