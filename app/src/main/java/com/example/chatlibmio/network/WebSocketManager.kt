package com.example.chatlibmio.network

import android.util.Log
import okhttp3.*
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class WebSocketManager(private val listener: Listener) {
    private val client = OkHttpClient()
    private var webSocket: WebSocket? = null

    interface Listener {
        fun onMessageReceived(message: String)
    }

    fun connect() {
        val request = Request.Builder()
            .url("wss://echo.websocket.org")
            .build()

        Log.d("WebSocketManager", "Connecting to WebSocket...")
        webSocket = client.newWebSocket(request, object : WebSocketListener() {

            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                Log.d("WebSocketManager", "WebSocket Opened: ${response.message}")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                Log.d("WebSocketManager", "Message received: $text")
                val interpreted = if (text.trim() == "203 = 0xcb") {
                    "Special message received!"
                } else text
                listener.onMessageReceived(interpreted)
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                Log.d("WebSocketManager", "WebSocket closing: $reason")
                webSocket.close(1000, null)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                Log.e("WebSocketManager", "WebSocket Failure: ${t.message}")
                t.printStackTrace()
            }
        })
    }

    fun send(message: String) {
        Log.d("WebSocketManager", "Sending message: $message")
        webSocket?.send(message)
    }

    fun close() {
        Log.d("WebSocketManager", "Closing WebSocket...")
        webSocket?.close(1000, null)
    }
}
