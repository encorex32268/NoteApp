package com.lihan.noteapp.core.domain

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Long.timestampToTimeString(): String {
    val dateFormat = DateTimeFormatter
        .ofPattern("yyyy/MM/dd HH:mm:ss")
        .withZone(ZoneId.systemDefault())
    return dateFormat.format(Instant.ofEpochMilli(this))
}