package com.example.chatlibmio

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatlibmio.adapter.ChatAdapter
import com.example.chatlibmio.model.Message
import com.example.chatlibmio.network.WebSocketManager
import java.time.LocalDateTime

class ChatActivity : AppCompatActivity(), WebSocketManager.Listener {

    private lateinit var adapter: ChatAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var messageInput: EditText
    private lateinit var sendButton: Button
    private lateinit var webSocketManager: WebSocketManager
    private val messages = mutableListOf<Message>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        recyclerView = findViewById(R.id.recyclerView)
        messageInput = findViewById(R.id.messageInput)
        sendButton = findViewById(R.id.sendButton)

        adapter = ChatAdapter(messages)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        webSocketManager = WebSocketManager(this)
        webSocketManager.connect()

        sendButton.setOnClickListener {
            val msg = messageInput.text.toString()
            if (msg.isNotBlank()) {
                val message = Message(
                    content = msg,
                    dateTime = LocalDateTime.now(),
                    isSentByUser = true
                )
                messages.add(message)
                adapter.notifyItemInserted(messages.size - 1)
                webSocketManager.send(msg)
                messageInput.text.clear()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        webSocketManager.close()
    }

    override fun onMessageReceived(message: String) {
        runOnUiThread {
            val msg = Message(
                content = message,
                dateTime = LocalDateTime.now(), // ⏰ Current date
                isSentByUser = false
            )
            messages.add(msg)
            adapter.notifyItemInserted(messages.size - 1)
        }
    }

}
