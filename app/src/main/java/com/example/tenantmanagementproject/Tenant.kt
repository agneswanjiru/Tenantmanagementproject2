package com.example.tenantmanagementproject

data class Tenant(
    val name: String,
    val phone: String,
    val rent: Double
) {
    fun summary(): String = "Phone: $phone\nRent: KSh ${"%.2f".format(rent)}"
}