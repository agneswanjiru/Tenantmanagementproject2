package com.example.tenantmanagementproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.tenantmanagementproject.databinding.FragmentViewTenantsBinding

class ViewTenantsFragment : Fragment() {
    private var _binding: FragmentViewTenantsBinding? = null
    private val binding get() = _binding!!

    // SAFE ARGS: Reads the arguments passed from AddTenantFragment
    private val args: ViewTenantsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewTenantsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // The tenant sent from Add Tenant, or null if we came from the Dashboard
        val newTenant = args.newTenant

        // DATA BINDING: Assign the tenant to the XML variable
        binding.tenant = newTenant

        // If no new tenant was passed, hide the "Just added" group
        if (newTenant == null) {
            binding.newTenantGroup.visibility = View.GONE
        }

        // Implicit Intent: Open phone dialer when CALL TENANT is tapped
        binding.callButton.setOnClickListener {
            if (newTenant != null) {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${newTenant.phone}"))
                startActivity(intent)
            }
        }

        // Display all saved tenants in the list
        val tenants = TenantRepository.tenants
        if (tenants.isEmpty()) {
            binding.allTenantsTextView.text = "No tenants yet. Add one from the Dashboard."
        } else {
            binding.allTenantsTextView.text = tenants.joinToString("\n\n") { it.summary() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}