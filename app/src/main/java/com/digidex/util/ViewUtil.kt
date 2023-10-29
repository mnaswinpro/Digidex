package com.digidex.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import coil.load

/**
 * Helper method to show a view
 */
fun View.show() {
    this.visibility = View.VISIBLE
}

/**
 * Helper method to hide a view
 */
fun View.hide() {
    this.visibility = View.GONE
}

/**
 * Helper method to set text and show the [TextView]
 */
fun TextView.setTextAndShow(value: String?) {
    if (value.isNullOrEmpty()) {
        this.hide()
    } else {
        this.text = value
        this.show()
    }
}

/**
 * Helper method to load image into an [ImageView] using Coil library with an imageUrl
 */
fun ImageView.loadImageAndShow(imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) {
        this.hide()
    } else {
        this.load(imageUrl)
        this.show()
    }
}
