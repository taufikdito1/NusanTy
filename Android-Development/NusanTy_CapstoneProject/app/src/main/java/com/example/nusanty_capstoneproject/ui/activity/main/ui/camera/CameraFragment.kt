package com.example.nusanty_capstoneproject.ui.activity.main.ui.camera

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.nusanty_capstoneproject.databinding.FragmentCameraBinding
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class CameraFragment : Fragment() {

    private var _binding: FragmentCameraBinding? = null
    private lateinit var photoPath: String
    private var getFile: File? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        getPermission()
        _binding = FragmentCameraBinding.inflate(inflater, container, false)

        binding.btnGalley.setOnClickListener {
            galleryStart()
        }

        binding.btnCamera.setOnClickListener {
            cameraStart()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions[Manifest.permission.CAMERA] ?: false -> {
                    getPermission()
                }
                else -> {
                    getPermission()
                }
            }
        }

    private fun getPermission() {
        if (checkPermission(Manifest.permission.CAMERA)
        ) {
            return
        } else {
            requestPermissionLauncher.launch(arrayOf(REQUIRED_PERMISSION))
        }
    }

    private fun galleryStart() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val pilih = Intent.createChooser(intent, "Which one")
        openGaleri.launch(pilih)
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun cameraStart() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(requireActivity().packageManager)

        createTempFile(requireContext()).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                requireContext(),
                "com.example.nusanty_capstoneproject",
                it
            )
            photoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            openCamera.launch(intent)
        }
    }


    private val openCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            getFile = File(photoPath)

            val result = BitmapFactory.decodeFile(getFile?.path)
            binding.imgScanImage.setImageBitmap(result)
        }
    }

    private val openGaleri = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            getFile = context?.let { uriToFile(selectedImg, it) }
            binding.imgScanImage.setImageURI(selectedImg)
        }
    }

    private fun uriToFile(selected: Uri, context: Context): File {
        val contentResolver: ContentResolver = context.contentResolver
        val myFile = createTempFile(context)

        val inputStream = contentResolver.openInputStream(selected) as InputStream
        val outputStream: OutputStream = FileOutputStream(myFile)
        val buf = ByteArray(1024)
        var len: Int
        while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
        outputStream.close()
        inputStream.close()

        return myFile
    }

    private fun reduceImgSize(file: File): File {
        val bitmap = BitmapFactory.decodeFile(file.path)
        var compressQuality = 100
        var streamLength: Int

        do {
            val bmpStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)
            val bmpPicByteArray = bmpStream.toByteArray()
            streamLength = bmpPicByteArray.size
            compressQuality -= 5
        } while (streamLength > 1000000)

        bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, FileOutputStream(file))
        return file
    }

    private fun createTempFile(context: Context): File {
        val Dir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(timeStamp, ".jpg", Dir)
    }

    private val timeStamp: String = SimpleDateFormat(
        DATE_FORMAT, Locale.US
    ).format(System.currentTimeMillis())

    companion object {
        private val REQUIRED_PERMISSION = Manifest.permission.CAMERA
        private const val DATE_FORMAT = "dd-MMM-yyyy"
    }
}