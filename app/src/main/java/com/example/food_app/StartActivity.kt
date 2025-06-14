package com.example.food_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity) // Make sure your XML file is named correctly

        // Find the Get Started button
        val btnGetStarted: Button = findViewById(R.id.btnGetStarted)

        // On button click, move to LoginActivity
        btnGetStarted.setOnClickListener {
            val intent = Intent(this@StartActivity, LoginActivity::class.java)
            startActivity(intent)
            finish() // Optional: to prevent going back to this screen
        }
    }
}
