package com.lihan.noteapp.core.domain

import java.time.LocalDateTime
import java.time.ZoneId

object Timestamp {
    fun getNowTimestamp(): Long {
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()
    }
}
