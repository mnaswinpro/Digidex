package com.digidex.util

import android.widget.Toast
import com.digidex.base.BaseFragment

/**
 * Extension function get strings from string.xml resources with an id
 */
fun BaseFragment.getResourceString(id: Int): String {
    return context?.getString(id).orEmpty()
}

/**
 * Extension function to show a [Toast] message to user
 */
fun BaseFragment.showMessageToUser(id: Int) {
    Toast.makeText(context, getResourceString(id), Toast.LENGTH_SHORT).show()
}
