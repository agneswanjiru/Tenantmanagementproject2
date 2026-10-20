package com.example.tenantmanagementproject

import java.io.Serializable

data class Tenant(
    val name: String,
    val phone: String,
    val rent: Double
) : Serializable {
    fun summary(): String {
        return "Tenant: $name\nPhone: $phone\nRent: KSh $rent"
    }
}