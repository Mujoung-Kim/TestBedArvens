package com.todaysquare.restapiexample.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView

import com.cometchat.pro.models.BaseMessage
import com.cometchat.pro.models.TextMessage
import com.todaysquare.restapiexample.R

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class MessagesAdapter(private val userID: String, private var messages: MutableList<BaseMessage>) :
    RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>() {
    companion object {
        private const val SENT = 0
        private const val RECEIVED = 1

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = when (viewType) {
            SENT -> LayoutInflater.from(parent.context)
                .inflate(R.layout.item_sent, parent, false)
            else -> LayoutInflater.from(parent.context)
                .inflate(R.layout.item_received, parent, false)

        }
        return MessageViewHolder(view)

    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        var messageDate: String? = null
        val currDate = getDate(messages[position].sentAt)

        if (position == 0)
            messageDate = currDate
        else {
            val nextDate = getDate(messages[position - 1].sentAt)

            if (currDate != nextDate) messageDate = currDate

        }
        holder.bind(messages[position], messageDate)

    }

    override fun getItemViewType(position: Int): Int =
        if (messages[position].sender?.uid!!.contentEquals(userID)) SENT else RECEIVED


    fun appendMessage(message: TextMessage) {
        this.messages.add(message)
        notifyItemInserted(this.messages.size - 1)

    }

    fun prependMessages(messages: List<BaseMessage>) {
        this.messages.addAll(0, messages)
        notifyDataSetChanged()

    }

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageText = itemView.findViewById<TextView>(R.id.message_text)
        private val timeText = itemView.findViewById<TextView>(R.id.message_timestamp)
        private val dateText = itemView.findViewById<TextView>(R.id.message_date)

        fun bind(message: BaseMessage, showDate: String?) {
            if (message is TextMessage)
                messageText.text = message.text
            timeText.text = getTime(message.sentAt)

            if (showDate != null) {
                dateText.isGone = false
                dateText.text = showDate

            } else dateText.isGone = true

        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getTime(long: Long): String? {
        return try {
            val simpleDateFormat = SimpleDateFormat("h:mm a")
            val netDate = Date(long.times(1000))

            simpleDateFormat.format(netDate)

        } catch (error: Exception) {
            error.toString()

        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(long: Long): String? {
        return try {
            val simpleDateFormat = SimpleDateFormat("yyyy년 M월 d일 E")
            val netDate = Date(long.times(1000))

            simpleDateFormat.format(netDate)

        } catch (error: Exception) {
            error.toString()

        }
    }
}