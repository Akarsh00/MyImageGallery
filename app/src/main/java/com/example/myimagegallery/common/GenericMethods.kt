package com.example.myimagegallery.common

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes


fun Context.showToast(@StringRes message: Int) {
    Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show()
}