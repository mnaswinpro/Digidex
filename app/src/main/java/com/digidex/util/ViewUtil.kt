package com.digidex.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import coil.load

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.isVisible(): Boolean {
    return this.visibility == View.VISIBLE
}

fun View.isHidden(): Boolean {
    return this.visibility == View.GONE
}

fun TextView.setTextAndShow(value: String?) {
    if (value.isNullOrEmpty()) {
        this.hide()
    } else {
        this.text = value
        this.show()
    }
}

fun ImageView.loadImageAndShow(imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) {
        this.hide()
    } else {
        this.load(imageUrl)
        this.show()
    }
}
