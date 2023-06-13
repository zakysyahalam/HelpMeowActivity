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

    private lateinit var photoImageView: CircleImageView
    private lateinit var logoutBtn: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        photoImageView = findViewById(R.id.photoImageView)

        photoImageView.setOnClickListener {
            openImageSelection()
        }

        nameTextView = findViewById(R.id.nameTextView)
        emailTextView = findViewById(R.id.emailTextView)

        sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext)

        findViewById<ImageButton>(R.id.navigation_home).setOnClickListener(bottomButtons)
        findViewById<ImageButton>(R.id.navigation_breed).setOnClickListener(bottomButtons)
        findViewById<ImageButton>(R.id.navigation_favorite).setOnClickListener(bottomButtons)
        findViewById<ImageButton>(R.id.navigation_profile).setOnClickListener(bottomButtons)
        findViewById<ImageButton>(R.id.navigation_post).setOnClickListener(bottomButtons)

        showProfile()
    }

    fun showProfile() {
        val email = sharedPref.getString("email", "")
        val username = sharedPref.getString("username", "")

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
    private fun openImageSelection() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_SELECTION)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_SELECTION && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri = data.data
            // Do something with the selected image URI, such as displaying it in the ImageView
            photoImageView.setImageURI(selectedImageUri)
        }
    }

    companion object {
        private const val REQUEST_IMAGE_SELECTION = 1
    }

    /*private fun logout() {
        val logoutApi = Retro().getRetroClientInstance().create(LogoutApi::class.java) // Replace 'retrofit' with your Retrofit instance

        val id = sharedPref.getString("id", "") // Retrieve the user ID from SharedPreferences

        if (id != null) {
            logoutApi.logout(id).enqueue(object : Callback<LogoutResponse> {
                override fun onResponse(call: Call<LogoutResponse>, response: Response<LogoutResponse>) {
                    if (response.isSuccessful) {
                        // Handle successful logout

                        // Clear the user data from SharedPreferences or perform any necessary cleanup

                        // Redirect to login screen
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
        }
    }*/

}