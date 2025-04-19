package com.example.chatlibmio.model

import com.example.chatlibmio.R
import java.util.Date

data class Message(
    val text: String,
    val timestamp: Date,
    val nickname: String = "ioiiiomio",
    val profilePicResId: Int = R.drawable.ic_dummy_profile,
    val sender: String
)
