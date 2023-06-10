package com.example.helpmeow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class BreedActivity: Activity() {

    private val bottomButtons: BottomButtons = BottomButtons()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breed)

        findViewById<ImageButton>(R.id.navigation_home).setOnClickListener(bottomButtons)
        findViewById<ImageButton>(R.id.navigation_breed).setOnClickListener(bottomButtons)
        findViewById<ImageButton>(R.id.navigation_favorite).setOnClickListener(bottomButtons)
        findViewById<ImageButton>(R.id.navigation_profile).setOnClickListener(bottomButtons)
        findViewById<ImageButton>(R.id.navigation_post).setOnClickListener(bottomButtons)
    }

    inner class BottomButtons : View.OnClickListener {
        override fun onClick(view: View) {
            when (view.id) {
                R.id.navigation_home -> {
                    val intent = Intent(this@BreedActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.navigation_breed -> {
                    val intent = Intent(this@BreedActivity, BreedActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.navigation_favorite -> {
                    val intent = Intent(this@BreedActivity, FavoriteActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.navigation_profile -> {
                    val intent = Intent(this@BreedActivity, ProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.navigation_post -> {
                    val intent = Intent(this@BreedActivity, PostActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }
        }
    }
}