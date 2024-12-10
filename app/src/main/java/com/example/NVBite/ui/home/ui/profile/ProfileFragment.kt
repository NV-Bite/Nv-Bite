package com.example.NVBite.ui.home.ui.profile

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.NVBite.R
import com.example.NVBite.data.locals.AccountPreferences
import com.example.NVBite.databinding.FragmentProfileBinding
import com.example.NVBite.ui.home.HomeActivity
import com.example.NVBite.ui.starter.MainActivity
import com.example.NVBite.utils.Constants
import com.example.NVBite.utils.Result

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var progressDialog: AlertDialog

    private val profileViewModel by viewModels<ProfileViewModel> {
        ProfileViewModel.Build(requireContext())
    }

    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            (result.data?.data as Uri).let { uri ->
                profileViewModel.updateProfilePic(
                    uriToBase64(uri) ?: AccountPreferences.preferencesDefaultValue
                ).observe(viewLifecycleOwner) { result ->
                    when(result) {
                        is Result.Loading -> {
                            showLoadingDialog()
                        }

                        is Result.Success -> {
                            hideLoadingDialog()
                            showToast("Profile Image successfully updated!")
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
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        observeViewModel()
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

    private fun observeViewModel() {
        profileViewModel.apply {
            getProfilePic().observe(viewLifecycleOwner) {
                if (it == AccountPreferences.preferencesDefaultValue) {
                    binding.ivProfile.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.dummy_profile))
                } else {
                    binding.ivProfile.setImageBitmap(base64ToBitmap(it))
                }
            }

            binding.apply {
                edName.setText(getName)
                edPhone.setText(getPhone)
                edPassword.setText(getPassword)
                edEmail.setText(getEmail)
            }
        }
    }

    private fun setListeners() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }

            btnAddPhoto.setOnClickListener {
//                val intent = Intent()
//                intent.action = Intent.ACTION_GET_CONTENT
//                intent.type = "image/*"
//                val chooser = Intent.createChooser(intent, "")
//                galleryLauncher.launch(chooser)
            }

            btnLogout.setOnClickListener {
                profileViewModel.clearPreferences()
                requireActivity().finishAffinity()
                startActivity(Intent(requireActivity(), MainActivity::class.java))
            }
        }
    }

    private fun uriToBase64(uri: Uri): String? {
        return try {
            val inputStream = requireActivity().contentResolver.openInputStream(uri)
            val bytes = inputStream?.readBytes()
            inputStream?.close()
            Base64.encodeToString(bytes, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun base64ToBitmap(base64String: String): Bitmap? {
        return try {
            val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        } catch (e: Exception) {
            e.printStackTrace()
            null
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