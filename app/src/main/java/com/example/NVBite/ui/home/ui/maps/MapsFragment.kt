package com.example.NVBite.ui.home.ui.maps

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.NVBite.R
import com.example.NVBite.databinding.FragmentMapsBinding
import com.example.NVBite.ui.home.HomeActivity
import com.example.NVBite.ui.home.adapters.EcoFoodAdapter
import com.example.NVBite.ui.home.adapters.MapAdapter
import com.example.NVBite.ui.home.adapters.MapsAdapter
import com.example.NVBite.ui.starter.MainActivity
import com.example.NVBite.utils.Constants

class MapsFragment : Fragment() {

    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!

    private val mapsAdapter = MapAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as HomeActivity).clearAllActiveMenu()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
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

            mapsAdapter.submitList(Constants.orphanages)
            mapsAdapter.onMapClick = { mapUrl: String ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl))
                intent.resolveActivity(requireContext().packageManager)?.let {
                    requireContext().startActivity(intent)
                }
            }

            mapsAdapter.onPhoneClick = { phoneNumber: String ->
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                intent.resolveActivity(requireContext().packageManager)?.let {
                    requireContext().startActivity(intent)
                }
            }

            rvMaps.apply {
                adapter = mapsAdapter
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