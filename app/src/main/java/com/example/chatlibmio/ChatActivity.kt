package com.example.chatlibmio

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatlibmio.adapter.MessageAdapter
import com.example.chatlibmio.model.Message
import java.util.Date

class ChatActivity : AppCompatActivity() {

    private lateinit var messageInput: EditText
    private lateinit var sendButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MessageAdapter
    private val messages = mutableListOf<Message>() // Replace with your Message model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        messageInput = findViewById(R.id.editMessage)
        sendButton = findViewById(R.id.btnSend)
        recyclerView = findViewById(R.id.recyclerChat)

        adapter = MessageAdapter(messages) // Your custom adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        sendButton.setOnClickListener {
            val messageText = messageInput.text?.toString()?.trim()

            if (!messageText.isNullOrEmpty()) {
                val newMessage = Message(
                    text = messageText,
                    sender = "You",
                    timestamp = Date())

                messages.add(newMessage)
                adapter.notifyItemInserted(messages.size - 1)
                recyclerView.scrollToPosition(messages.size - 1)
                messageInput.text?.clear()
            }
        }

    }
}
