package com.example.toolbarchanges

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.showShortToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Fragment.showShortToast(text: String) {
    Toast.makeText(this.requireActivity(), text, Toast.LENGTH_SHORT).show()
}