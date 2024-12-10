package com.example.NVBite.ui.home.ui.result

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.NVBite.R
import com.example.NVBite.data.models.DetectImageResponse
import com.example.NVBite.databinding.FragmentResultBinding
import com.example.NVBite.ui.home.HomeActivity
import java.io.File

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    private var imageFile: File = File("")
    private var detectImageResponse: DetectImageResponse = DetectImageResponse()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as HomeActivity).clearAllActiveMenu()
        arguments?.let {
            imageFile = ResultFragmentArgs.fromBundle(it).imageFile as File
            detectImageResponse = ResultFragmentArgs.fromBundle(it).detectImageRespone
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.ivResult.setImageBitmap(BitmapFactory.decodeFile(imageFile.path))

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
            tvTitle.text = detectImageResponse.predictedClass

            val formattedConfidence = String.format("%.2f", detectImageResponse.confidence) + "%"
            tvAccuration.text = formattedConfidence

            webView.settings.javaScriptEnabled = true
            webView.webViewClient = WebViewClient()
            webView.loadDataWithBaseURL(
                null, detectImageResponse.genText ?: "", "text/html", "UTF-8", null
            )
        }
    }

    private fun setListeners() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }

            btnDropPoint.setOnClickListener {
                val action = ResultFragmentDirections.actionNavigationResultToNavigationMaps()
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}