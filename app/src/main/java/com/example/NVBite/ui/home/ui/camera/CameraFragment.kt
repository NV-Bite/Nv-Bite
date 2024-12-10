package com.example.NVBite.ui.home.ui.camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import com.example.NVBite.utils.Result
import android.media.ExifInterface
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.NVBite.R
import com.example.NVBite.databinding.FragmentCameraBinding
import com.example.NVBite.ui.home.HomeActivity
import com.example.NVBite.utils.Constants
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Locale

class CameraFragment : Fragment() {

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!
    private lateinit var progressDialog: AlertDialog

    private lateinit var imagePathLocation: String

    private val cameraViewModel by viewModels<CameraViewModel> {
        CameraViewModel.Build(requireContext())
    }

    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val file = File(imagePathLocation)
            file.let { image ->
                val bitmap =
                    rotateImage(BitmapFactory.decodeFile(image.path), imagePathLocation)
                postImage(bitmap.toFile())
            }
        }
    }

    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            postImage((result.data?.data as Uri).toFile())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        val root: View = binding.root

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

    private fun setListeners() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }

            btnUpload.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_GET_CONTENT
                intent.type = "image/*"
                val chooser = Intent.createChooser(intent, "")
                galleryLauncher.launch(chooser)
            }

            btnCamera.setOnClickListener {
                if (checkImagePermission()) {
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    intent.resolveActivity(requireActivity().packageManager)
                    val storageDir: File? =
                        requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    val customTempFile = File.createTempFile(
                        SimpleDateFormat(
                            "dd-MMM-yyyy",
                            Locale.US
                        ).format(System.currentTimeMillis()), ".jpg", storageDir
                    )
                    customTempFile.also {
                        imagePathLocation = it.absolutePath
                        intent.putExtra(
                            MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(
                                requireActivity(),
                                requireActivity().application.packageName,
                                it
                            )
                        )
                        cameraLauncher.launch(intent)
                    }
                } else {
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        REQUIRED_CAMERA_PERMISSION,
                        REQUEST_CODE_PERMISSIONS
                    )
                }
            }
        }
    }

    private fun postImage(file: File) {
        cameraViewModel.detectImage(
            file
        )
            .observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Result.Loading -> {
                        showLoadingDialog()
                    }

                    is Result.Success -> {
                        hideLoadingDialog()
                        val action = CameraFragmentDirections.actionNavigationCameraToNavigationResult(
                            imageFile = file,
                            detectImageRespone = result.data
                        )
                        findNavController().navigate(action)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkImagePermission() = REQUIRED_CAMERA_PERMISSION.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    private fun rotateFile(file: File, isBackCamera: Boolean = false) {
        val matrix = Matrix()
        val bitmap = BitmapFactory.decodeFile(file.path)
        val rotation = if (isBackCamera) 90f else -90f
        matrix.postRotate(rotation)
        if (!isBackCamera) {
            matrix.postScale(-1f, 1f, bitmap.width / 2f, bitmap.height / 2f)
        }
        val result = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        result.compress(Bitmap.CompressFormat.JPEG, 100, FileOutputStream(file))
    }

    private fun Uri.toFile(): File {
        val tempFile = File.createTempFile(
            "IMG_${System.currentTimeMillis()}_",
            ".jpg",
            requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )

        requireActivity().contentResolver.openInputStream(this)?.use { input ->
            FileOutputStream(tempFile).use { output ->
                input.copyTo(output)
            }
        }

        return tempFile
    }

    private fun rotateImage(bitmap: Bitmap, path: String): Bitmap {
        val matrix = Matrix()
        when (ExifInterface(path).getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_UNDEFINED
        )) {
            ExifInterface.ORIENTATION_ROTATE_90 -> matrix.setRotate(90f)
            ExifInterface.ORIENTATION_ROTATE_180 -> matrix.setRotate(180f)
        }

        return Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            bitmap.height,
            matrix,
            true
        )
    }

    private fun Bitmap.toFile(): File {
        val tempFile = File.createTempFile(
            "IMG_${System.currentTimeMillis()}_",
            ".jpg",
            requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )

        val bos = ByteArrayOutputStream()
        this.compress(Bitmap.CompressFormat.PNG, 0, bos)
        val bitmapdata = bos.toByteArray();

        val fos = FileOutputStream(tempFile);
        fos.write(bitmapdata);
        fos.flush();
        fos.close();

        return tempFile
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

    companion object {
        private val REQUIRED_CAMERA_PERMISSION = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 101
    }
}