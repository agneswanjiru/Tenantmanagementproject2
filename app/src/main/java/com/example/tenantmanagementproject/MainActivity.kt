package com.example.tenantmanagementproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.tenantmanagementproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var lastTenant: Tenant? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loggedInEmail = intent.getStringExtra("EMAIL")
        if (!loggedInEmail.isNullOrEmpty()) {
            Toast.makeText(this, "Logged in as $loggedInEmail", Toast.LENGTH_SHORT).show()
        }

        binding.saveButton.setOnClickListener {
            val name = binding.tenantNameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val rentText = binding.rentEditText.text.toString().trim()

            if (name.isEmpty()) { binding.tenantNameEditText.error = "Required"; return@setOnClickListener }
            if (phone.isEmpty()) { binding.phoneEditText.error = "Required"; return@setOnClickListener }
            if (rentText.isEmpty()) { binding.rentEditText.error = "Required"; return@setOnClickListener }

            val rent = rentText.toDoubleOrNull()
            if (rent == null) {
                binding.rentEditText.error = "Enter a number"
                return@setOnClickListener
            }

            val tenant = Tenant(name, phone, rent)
            binding.tenant = tenant
            lastTenant = tenant
        }

        binding.callButton.setOnClickListener {
            val tenant = lastTenant
            if (tenant == null) {
                Toast.makeText(this, "Save a tenant first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(Intent.ACTION_DIAL, "tel:${tenant.phone}".toUri())
            startActivity(intent)
        }
    }
}