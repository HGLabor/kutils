package de.hglabor.utils.kutils

import java.util.concurrent.TimeUnit

fun Long.formatAsTime(): String {
    val minutes = TimeUnit.SECONDS.toMinutes(this)
    return String.format("%02d:%02d", minutes, TimeUnit.SECONDS.toSeconds(this)-60*minutes)
}