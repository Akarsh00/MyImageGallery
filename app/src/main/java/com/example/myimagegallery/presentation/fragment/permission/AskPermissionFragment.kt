package com.example.myimagegallery.presentation.fragment.permission

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myimagegallery.R
import com.example.myimagegallery.common.PermissionHelper
import com.example.myimagegallery.common.showToast
import com.example.myimagegallery.databinding.AskPermissionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AskPermissionFragment : Fragment(R.layout.ask_permission) {
    private val CAMERA_PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE
    private val permissionHelper = PermissionHelper(this)
    lateinit var binding: AskPermissionBinding
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                handlePermissionResult(isGranted)
            }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AskPermissionBinding.bind(view)
        requestPermissionLauncher.launch(CAMERA_PERMISSION)

        binding.btnPermission.setOnClickListener {

            // Check and request the permission
            requestPermissionLauncher.launch(CAMERA_PERMISSION)

        }
    }

    private fun handlePermissionResult(isGranted: Boolean) {
        if (isGranted) {
            // Permission granted, perform your actions here
            findNavController().navigate(R.id.permission_to_image_framgent)
        } else {
            if (shouldShowRequestPermissionRationale(CAMERA_PERMISSION)) {
                context?.showToast(R.string.permission_deny_toast_message)
                // Permission denied, show an explanation or take appropriate action
            } else {
                context?.showToast(R.string.permission_completely_deny_toast_message)
                // Permission permanently denied, inform the user and provide options
            }
        }
    }

}