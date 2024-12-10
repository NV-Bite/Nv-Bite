package com.example.NVBite.ui.home.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.NVBite.R
import com.example.NVBite.databinding.FragmentHistoryBinding
import com.example.NVBite.ui.home.HomeActivity
import com.example.NVBite.ui.home.adapters.HistoriesAdapter
import com.example.NVBite.utils.Constants
import com.example.NVBite.utils.Result

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var progressDialog: AlertDialog

    private val historyViewModel by viewModels<HistoryViewModel> {
        HistoryViewModel.Build(requireContext())
    }

    private val historyAdapter = HistoriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        observeViewModels()
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

    private fun observeViewModels() {
        historyViewModel.getHistories().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    showLoadingDialog()
                }

                is Result.Success -> {
                    hideLoadingDialog()
                    historyAdapter.submitList(result.data)
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
    }

    private fun setViews() {
        binding.apply {

            rvHistory.apply {
                adapter = historyAdapter
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}