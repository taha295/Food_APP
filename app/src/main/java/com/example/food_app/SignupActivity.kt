package com.example.food_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var eyeIcon: ImageView
    private lateinit var signUpButton: Button
    private lateinit var signInText: TextView
    private lateinit var emailEditText: EditText
    private lateinit var usernameEditText: EditText

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)

        // Views
        emailEditText = findViewById(R.id.editTextEmail)
        usernameEditText = findViewById(R.id.editTextUsernmae)
        passwordEditText = findViewById(R.id.editTextPassword)
        confirmPasswordEditText = findViewById(R.id.editTextConfirmPassword)
        eyeIcon = findViewById(R.id.eyeIcon)
        signUpButton = findViewById(R.id.buttonLogin)
        signInText = findViewById(R.id.textSignIn)

        // Toggle password visibility
        eyeIcon.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {
                passwordEditText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                confirmPasswordEditText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                eyeIcon.setImageResource(R.drawable.ic_eye_open)
            } else {
                passwordEditText.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                confirmPasswordEditText.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                eyeIcon.setImageResource(R.drawable.ic_eye_closed)
            }

            passwordEditText.setSelection(passwordEditText.text.length)
            confirmPasswordEditText.setSelection(confirmPasswordEditText.text.length)
        }

        // Sign Up button logic
        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            // Email validation
            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches() || !email.endsWith("@gmail.com")) {
                emailEditText.error = "Enter a valid Gmail address (e.g., abc@gmail.com)"
                return@setOnClickListener
            }

            // Username validation
            if (username.isEmpty()) {
                usernameEditText.error = "Enter username"
                return@setOnClickListener
            }

            // Password validation
            if (!isValidPassword(password)) {
                passwordEditText.error =
                    "Password must contain 1 uppercase, 1 lowercase, 1 special character, and be at least 6 characters long"
                return@setOnClickListener
            }

            // Password match
            if (password != confirmPassword) {
                confirmPasswordEditText.error = "Passwords do not match"
                return@setOnClickListener
            }

            // Save in SharedPreferences
            val sharedPref = getSharedPreferences("UserData", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("email", email)
                putString("password", password)
                apply()
            }

            Toast.makeText(this, "Signup Successful!", Toast.LENGTH_SHORT).show()

            // Navigate to MainActivity
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Go to Login Screen
        signInText.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    // Password strength check
    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=!]).{6,}\$")
        return passwordPattern.matches(password)
    }
}
