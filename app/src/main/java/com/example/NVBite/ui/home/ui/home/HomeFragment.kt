package com.example.NVBite.ui.home.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.NVBite.R
import com.example.NVBite.data.locals.AccountPreferences
import com.example.NVBite.databinding.FragmentHomeBinding
import com.example.NVBite.ui.home.HomeActivity
import com.example.NVBite.ui.home.ui.camera.CameraFragmentDirections
import com.example.NVBite.utils.Constants
import com.example.NVBite.utils.Result

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var progressDialog: AlertDialog

    private val homeViewModel by viewModels<HomeViewModel> {
        HomeViewModel.Build(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        observeUsers()
        setListeners()

        return root
    }

    private fun observeUsers() {
        if (homeViewModel.getName == AccountPreferences.preferencesDefaultValue) {
            homeViewModel.getProfileInfo().observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Result.Loading -> {
                        showLoadingDialog()
                    }

                    is Result.Success -> {
                        binding.apply {
                            tvUsername.text = result.data.name
                        }
                        hideLoadingDialog()
                    }

                    is Result.Error -> {
                        hideLoadingDialog()
                        showToast(result.error)
                    }

                    is Result.Unauthorized -> {
                        hideLoadingDialog()
                        showToast(result.error)
                        Constants.handleUnauthorizedUser(requireActivity())
                    }
                }
            }
        } else {
            binding.tvUsername.text = homeViewModel.getName
        }
    }

    private fun setListeners() {
        binding.apply {
            btnScanHere.setOnClickListener {
                findNavController().navigate(R.id.navigation_camera)
                (requireActivity() as HomeActivity).navView.menu.getItem(1).isChecked = true
            }

            btnEcoFriendly.setOnClickListener {
                findNavController().navigate(R.id.navigation_eco_food)
            }
        }
    }

    private fun showLoadingDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(R.layout.progress_dialog)
        builder.setCancelable(false)
        progressDialog = builder.create()
        progressDialog.show()
    }

    private fun hideLoadingDialog() {
        progressDialog.dismiss()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}