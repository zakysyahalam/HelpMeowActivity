package com.example.helpmeow

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.helpmeow.Object
import com.example.helpmeow.R
import com.example.helpmeow.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostBinding
    private lateinit var adapterRole: ArrayAdapter<String>
    private lateinit var adapterBreed: ArrayAdapter<String>
    private lateinit var adapterGender: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterRole = ArrayAdapter(this, R.layout.item_list, Object.role)
        adapterBreed = ArrayAdapter(this, R.layout.item_list, Object.breed)
        adapterGender = ArrayAdapter(this, R.layout.item_list, Object.gender)

        binding.roleEditText.setAdapter(adapterRole)
        binding.breedEditText.setAdapter(adapterBreed)
        binding.genderEditText.setAdapter(adapterGender)
    }
}
