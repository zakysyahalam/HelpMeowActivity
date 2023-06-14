package com.example.helpmeow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.provider.MediaStore
import android.widget.*
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {

    private val bottomButtons: BottomButtons = BottomButtons()
    private lateinit var nameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var sharedPref: SharedPreferences

    private lateinit var logoutBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        nameTextView = findViewById(R.id.nameTextView)
        emailTextView = findViewById(R.id.emailTextView)

        sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext)

        findViewById<ImageButton>(R.id.navigation_home).setOnClickListener(bottomButtons)
        findViewById<ImageButton>(R.id.navigation_breed).setOnClickListener(bottomButtons)
        findViewById<ImageButton>(R.id.navigation_favorite).setOnClickListener(bottomButtons)
        findViewById<ImageButton>(R.id.navigation_profile).setOnClickListener(bottomButtons)
        findViewById<ImageButton>(R.id.navigation_post).setOnClickListener(bottomButtons)

        showProfile()
        logoutAction()
    }

    fun logoutAction(){
        val logoutButton = findViewById<TextView>(R.id.Logout)
        logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        val logoutApi = Retro().getRetroClientInstance().create(LogoutApi::class.java)

        val id = sharedPref.getString("yourId", "")

        if (!id.isNullOrEmpty()) {
            logoutApi.logout(id).enqueue(object : Callback<LogoutResponse> {
                override fun onResponse(call: Call<LogoutResponse>, response: Response<LogoutResponse>) {
                    if (response.isSuccessful) {
                        // Handle successful logout
                        Toast.makeText(this@ProfileActivity, "Logout Success", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this@ProfileActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // Handle error in logout API response
                        Toast.makeText(this@ProfileActivity, "Logout failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                    // Handle failure in making the logout API call
                    Toast.makeText(this@ProfileActivity, "Logout failed", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this@ProfileActivity, "User ID not available", Toast.LENGTH_SHORT).show()
        }
    }

    fun showProfile() {
        val email = sharedPref.getString("email", "")
        val username = sharedPref.getString("username", "")
        val yourId = sharedPref.getString("yourId", "")

        nameTextView.text = username
        emailTextView.text = email
    }

    inner class BottomButtons : View.OnClickListener {
        override fun onClick(view: View) {
            when (view.id) {
                R.id.navigation_home -> {
                    val intent = Intent(this@ProfileActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.navigation_breed -> {
                    val intent = Intent(this@ProfileActivity, BreedActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.navigation_favorite -> {
                    val intent = Intent(this@ProfileActivity, FavoriteActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.navigation_profile -> {
                    val intent = Intent(this@ProfileActivity, ProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.navigation_post -> {
                    val intent = Intent(this@ProfileActivity, PostActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}





