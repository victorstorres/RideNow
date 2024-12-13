package com.example.ridenow.util

fun Double.formatToTwoDecimalPlaces(): String {
    return "%.2f".format(this)
}

fun Double.formatToZeroDecimalPlaces(): String {
    return "%.0f".format(this)
}
