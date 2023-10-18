package com.digidex.ui.testUtil

import com.google.gson.Gson
import java.io.InputStreamReader

fun <T> load(clazz: Class<T>, file: String): T {
    val fixtureStreamReader = InputStreamReader(clazz.classLoader!!.getResourceAsStream(file))
    return Gson().fromJson(fixtureStreamReader, clazz)
}

fun loadAsString(file: String): String {
    val classLoader = Thread.currentThread().contextClassLoader
    return InputStreamReader(classLoader!!.getResourceAsStream(file)).readText()
}
