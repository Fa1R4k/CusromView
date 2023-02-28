package com.example.cusromview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cusromview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnChange.setOnClickListener {
                customView.switchMode()
            }
        }
    }
}