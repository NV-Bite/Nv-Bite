package com.example.NVBite.ui.home.ui.ecofood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.NVBite.R
import com.example.NVBite.databinding.FragmentEcoFoodBinding
import com.example.NVBite.databinding.FragmentHomeBinding
import com.example.NVBite.databinding.FragmentMapsBinding
import com.example.NVBite.databinding.FragmentProfileBinding
import com.example.NVBite.databinding.FragmentResultBinding
import com.example.NVBite.ui.home.HomeActivity
import com.example.NVBite.ui.home.adapters.EcoFoodAdapter
import com.example.NVBite.ui.starter.MainActivity

class EcoFoodFragment : Fragment() {

    private var _binding: FragmentEcoFoodBinding? = null
    private val binding get() = _binding!!

    private val ecoFoodAdapter = EcoFoodAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as HomeActivity).clearAllActiveMenu()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEcoFoodBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setViews()
        setListeners()

        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(),
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.navigation_home)
                    (requireActivity() as HomeActivity).navView.menu.getItem(0).isChecked = true
                }
            })

        return root
    }

    private fun setViews() {
        binding.apply {

            rvEcoFood.apply {
                adapter = ecoFoodAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    private fun setListeners() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}