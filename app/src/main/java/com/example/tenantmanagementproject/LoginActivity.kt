package com.example.tenantmanagementproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementproject.MainActivity
import com.example.tenantmanagementproject.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Receive email sent from RegisterActivity
        val receivedEmail = intent.getStringExtra("EMAIL")
        if (!receivedEmail.isNullOrEmpty()) {
            binding.emailEditText.setText(receivedEmail)
        }

        // LOGIN button
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("EMAIL", email)
            startActivity(intent)
            finish()
        }

        // "Register" link
        binding.registerTextView.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // "Need help? Visit our website"
        binding.helpTextView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.strathmore.edu"))
            startActivity(intent)
        }
    }
}