package com.jcastaneda.servicioweb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var urlbase = "http://10.0.2.2:4000/"
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)
        val loginButton: Button = findViewById(R.id.buttonLogin)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                login(email, password)
            } else {
                Toast.makeText(this, "Por favor pon tu email y contraseña", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login(email: String, password: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(urlbase)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PostApiService::class.java)
        val loginData = LoginData(email, password)

        val call = service.login(loginData)

        call.enqueue(object : Callback<PostModelResponse> {
            override fun onResponse(call: Call<PostModelResponse>, response: Response<PostModelResponse>) {
                if (response.isSuccessful) {
                    val user = response.body()


                    if (user != null) {
                        val userEmail = user.email
                        val userPassword = user.password

                        // Verifica que se estén obteniendo los valores correctamente
                        Log.d("LoginInfo", "Email: $userEmail, Password: $userPassword")

                        // Mostrar el email y la contraseña en un Toast (puedes usar otro método para mostrar la información)
                        Toast.makeText(this@MainActivity, "Email: $userEmail, Password: $userPassword", Toast.LENGTH_LONG).show()
                    }
                } else {
                    // Error en el inicio de sesión, maneja la respuesta no exitosa aquí
                    Toast.makeText(this@MainActivity, "Credenciales Invalidas", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PostModelResponse>, t: Throwable) {
                // Error de conexión, maneja el fallo en la llamada a la API aquí
                Toast.makeText(this@MainActivity, "Conection fallida pipipi", Toast.LENGTH_SHORT).show()
            }
        })
    }
}