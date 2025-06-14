package com.example.food_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var signUpText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in_activity) // Make sure your XML layout is correct

        // Views
        emailEditText = findViewById(R.id.emailEditText)   // Email EditText ID in login XML
        passwordEditText = findViewById(R.id.passwordEditText) // Password EditText ID in login XML
        loginButton = findViewById(R.id.loginBtn) // Button ID in login XML
        signUpText = findViewById(R.id.dontHaveAccountText)   // TextView ID for "Don't have account? Sign up"

        // Login Button Logic
        loginButton.setOnClickListener {
            val enteredEmail = emailEditText.text.toString().trim()
            val enteredPassword = passwordEditText.text.toString()

            // Get stored values
            val sharedPref = getSharedPreferences("UserData", Context.MODE_PRIVATE)
            val storedEmail = sharedPref.getString("email", null)
            val storedPassword = sharedPref.getString("password", null)

            // Validation
            if (enteredEmail.isEmpty() || enteredPassword.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            } else if (enteredEmail == storedEmail && enteredPassword == storedPassword) {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }

        // Navigate to SignUpActivity
        signUpText.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }}