package com.example.chatlibmio.model
import java.time.LocalDateTime

data class Message(
    val content: String,
    val dateTime: LocalDateTime,
    val isSentByUser: Boolean
)
