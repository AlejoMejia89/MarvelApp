package com.alejandromejia.marvelapp.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun String.formatDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    return try {
        val date = inputFormat.parse(this)
        date?.let { outputFormat.format(it) }.toString()
    } catch (e: Exception) {
        e.printStackTrace()
        this
    }
}