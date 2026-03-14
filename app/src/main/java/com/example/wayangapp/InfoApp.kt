package com.example.wayangapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wayangapp.databinding.ActivityInfoAppBinding

class InfoApp : AppCompatActivity() {

    private lateinit var binding: ActivityInfoAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoAppBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnBackToHome.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }
}