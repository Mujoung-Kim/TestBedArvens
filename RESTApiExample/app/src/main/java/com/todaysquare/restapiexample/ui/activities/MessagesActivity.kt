package com.todaysquare.restapiexample.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.cometchat.pro.constants.CometChatConstants
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.core.MessagesRequest
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.BaseMessage
import com.cometchat.pro.models.TextMessage
import com.todaysquare.restapiexample.R

import kotlinx.android.synthetic.main.activity_messages.*

class MessagesActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MessagesActivity"

    }
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var messagesAdapter: MessagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)

        this.title = intent.getStringExtra("RECEIVER_NAME")

        val receiverID = intent.getStringExtra("RECEIVER_ID")

        layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        messages.layoutManager = layoutManager

        messagesAdapter = MessagesAdapter(CometChat.getLoggedInUser().uid, mutableListOf())

        messages.adapter = messagesAdapter
        send_message.isEnabled = false
        enter_message.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s != null && s.isEmpty()) {
                    send_message.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.gray))
                    send_message.isEnabled = false

                } else {
                    send_message.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.colorAccent))
                    send_message.isEnabled = true

                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        send_message.setOnClickListener {
            if (send_message.isEnabled) sendMessage()

        }
        joinChat()
        setRecyclerViewScrollListener()

    }

    private val firstVisibleItemPosition: Int get() = layoutManager.findFirstVisibleItemPosition()

    private fun setRecyclerViewScrollListener() {
        messages.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!isFetchingMessages && 0 == firstVisibleItemPosition) {
                    Log.d(TAG, "Fetch messages")
                    fetchMessages()

                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        CometChat.addMessageListener(listenerID, object : CometChat.MessageListener() {
            override fun onTextMessageReceived(message: TextMessage) {
                if (message.sender.uid == receiverID) {
                    messagesAdapter.appendMessage(message)
                    scrollToBottom()
                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        CometChat.removeMessageListener(listenerID)

    }

    private fun fetchMessages() {
        isFetchingMessages = true
        var messagesRequest : MessagesRequest

        when (lastMessageID) {
            -1 -> messagesRequest = MessagesRequest.MessagesRequestBuilder()
                .setUID(receiverID)
                .setLimit(messagesFetchLimit)
                .build()

            else -> {
                messagesRequest = MessagesRequest.MessagesRequestBuilder()
                    .setUID(receiverID)
                    .setMessageId(lastMessageID)
                    .setLimit(messagesFetchLimit)
                    .build()

            }
        }

        messagesRequest?.fetchPrevious(object:CometChat.CallbackListener<List<BaseMessage>>(){
            override fun onSuccess(messages: List<BaseMessage>?) {
                if (!messages.isNullOrEmpty()) {
                    messagesAdapter.prependMessages(messages)
                    lastMessageID = messages.first().id
                    Log.d(TAG, "Messages received successfully")

//                    if (messages[0] is TextMessage){
//                        Log.d(TAG, "Text message received successfully: " + messages.toString())
//
//                    }
//                    if (messages[0] is MediaMessage){
//                        Log.d(TAG, "Media message received successfully: " + messages.toString())
//                    }
                }
                else Log.d(TAG, "Retrieved empty messages $lastMessageID")
                isFetchingMessages = false

            }

            override fun onError(p0: CometChatException?) {
                Log.d(TAG, "Message fetching failed with exception : ${p0?.message}")
                isFetchingMessages = false

            }
        })
    }

    private fun joinChat() {
        fetchMessages()

    }

    private fun sendMessage() {
        val textMessage = TextMessage(receiverID, enter_message.text.toString(),
            CometChatConstants.RECEIVER_TYPE_USER)

        CometChat.sendMessage(textMessage, object : CometChat.CallbackListener<TextMessage>() {
            override fun onSuccess(message: TextMessage) {
                enter_message.setText("")
                messagesAdapter.appendMessage(message)
                scrollToBottom()

            }
            override fun onError(e: CometChatException) {
                Log.d("CometChat", "Message send failed: ${e.message}")

            }
        })
    }

    private fun scrollToBottom() {
        messages.scrollToPosition(messagesAdapter.itemCount - 1)

    }
}
