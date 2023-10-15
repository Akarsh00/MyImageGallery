package com.example.myimagegallery.common

import androidx.fragment.app.Fragment

import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class PermissionHelper(private val fragment: Fragment) {

    private val PERMISSION_REQUEST_CODE = 1001

    fun requestPermission(permission: String, onPermissionGranted: () -> Unit, onPermissionDenied: () -> Unit, onPermissionPermanentlyDenied: () -> Unit) {
        if (isPermissionGranted(permission)) {
            onPermissionGranted()
        } else {
            if (shouldShowRequestPermissionRationale(permission)) {
                onPermissionDenied()
            } else {
                requestPermissionFromSystem(permission)
                fragment.requestPermissions(arrayOf(permission), PERMISSION_REQUEST_CODE)
            }
        }
    }

    private fun isPermissionGranted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(fragment.requireContext(), permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun shouldShowRequestPermissionRationale(permission: String): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(fragment.requireActivity(), permission)
    }

    private fun requestPermissionFromSystem(permission: String) {
        ActivityCompat.requestPermissions(fragment.requireActivity(), arrayOf(permission), PERMISSION_REQUEST_CODE)
    }


}