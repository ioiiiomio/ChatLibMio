package com.example.chatlibmio.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatlibmio.R
import com.example.chatlibmio.model.Message
import java.text.SimpleDateFormat
import java.util.*

class MessageAdapter(private val messages: MutableList<Message>) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageText: TextView = itemView.findViewById(R.id.messageText)
        val profileImage: ImageView = itemView.findViewById(R.id.profileImage)
        val nicknameText: TextView = itemView.findViewById(R.id.nicknameText)
        val timestampText: TextView = itemView.findViewById(R.id.timestampText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message, parent, false)
        return MessageViewHolder(view)
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]

        holder.messageText.text = message.text
        holder.profileImage.setImageResource(message.profilePicResId)
        holder.nicknameText.text = message.nickname

        val formatter = SimpleDateFormat("HH:mm, MMM d", Locale.getDefault())
        holder.timestampText.text = formatter.format(message.timestamp)
    }

    fun addMessage(message: Message) {
        messages.add(message)
        notifyItemInserted(messages.size - 1)
    }
}
