package com.example.helpmeow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : Activity() {


    private lateinit var et_email: EditText
    private lateinit var et_password: EditText
    private lateinit var google_btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        et_email = findViewById(R.id.enter_email)
        et_password = findViewById(R.id.enter_password)
        google_btn = findViewById(R.id.google_btn)


        val registerTextView: AppCompatTextView = findViewById(R.id.to_register)
        registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginAction()
    }

    fun loginAction(){
        val loginButton = findViewById<TextView>(R.id.login_btn)
        loginButton.setOnClickListener {
            login()
        }
    }


    fun login() {
        val request = LoginRequest()
        request.email = et_email.text.toString().trim()
        request.password = et_password.text.toString().trim()

        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        retro.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "100:Login Failure", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val user = response.body()
                val email = user?.email
                val yourId = user?.yourId
                val username = user?.username

                if (response.isSuccessful) {
                    val message = response.body()?.message
                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                    Toast.makeText(applicationContext, "your username: $username", Toast.LENGTH_SHORT).show()
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = try {
                        val errorJson = Gson().fromJson(errorBody, ErrorJson::class.java)
                        errorJson.message ?: "Unknown error"
                    } catch (e: Exception) {
                        e.printStackTrace()
                        errorBody ?: "Unknown error"
                    }
                    Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}