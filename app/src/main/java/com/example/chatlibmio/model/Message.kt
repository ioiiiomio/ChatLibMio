package com.example.chatlibmio.model

data class Message(
    val text: String,
    val timestamp: String,
    val nickname: String,
    val profilePicResId: Int // drawable resource ID
)
