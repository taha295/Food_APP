package com.example.food_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import me.ibrahimsn.lib.CirclesLoadingView

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DELAY: Long = 5000  // 5 seconds
    private lateinit var loadingView: CirclesLoadingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_activity)

        // Initialize loader
        loadingView = findViewById(R.id.loadingView)
        loadingView.visibility = View.VISIBLE  // Start animation (auto animates)

        // Delay and move to next screen
        Handler(Looper.getMainLooper()).postDelayed({
            loadingView.visibility = View.GONE  // Optional: stop animation
            startActivity(Intent(this, StartActivity::class.java))
            finish()
        }, SPLASH_DELAY)
    }
}
