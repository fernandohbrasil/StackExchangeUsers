package com.fernandohbrasil.stackexchange.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.toStringDateTime(): String {
    return try {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.UK)
        val netDate = Date(this * 1000)
        sdf.format(netDate)
    } catch (e: Exception) {
        e.toString()
    }
}