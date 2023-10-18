package com.digidex.util

import android.widget.Toast
import com.digidex.base.BaseFragment

fun BaseFragment.getResourceString(id: Int) : String {
    return context?.getString(id).orEmpty()
}

fun BaseFragment.showMessageToUser(id: Int) {
    Toast.makeText(context, getResourceString(id), Toast.LENGTH_SHORT).show()
}
