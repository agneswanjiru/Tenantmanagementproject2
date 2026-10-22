package com.example.tenantmanagementproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tenantmanagementproject.databinding.FragmentAddTenantBinding
import android.widget.Toast
import androidx.navigation.fragment.findNavController

class AddTenantFragment : Fragment() {
    private var _binding: FragmentAddTenantBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTenantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle SAVE button click: read inputs, save to repository, and navigate with Safe Args
        binding.saveButton.setOnClickListener {
            val name = binding.tenantNameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val rentStr = binding.rentEditText.text.toString().trim()
            val rent = rentStr.toDoubleOrNull() ?: 0.0

            if (name.isEmpty() || phone.isEmpty() || rentStr.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val tenant = Tenant(name, phone, rent)
            TenantRepository.addTenant(tenant)

            val action =
                AddTenantFragmentDirections.actionAddTenantFragmentToViewTenantsFragment(tenant)
            findNavController().navigate(action)
        }
    } // <-- onViewCreated closes here

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}