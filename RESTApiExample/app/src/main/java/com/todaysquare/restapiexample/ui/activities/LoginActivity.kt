package com.todaysquare.restapiexample.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat

import com.cometchat.pro.constants.CometChatConstants
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.User
import com.google.firebase.messaging.FirebaseMessaging
import com.todaysquare.restapiexample.R
import com.todaysquare.restapiexample.data.preferences.MySharedPreferences
import com.todaysquare.restapiexample.utils.showToast

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        join_chat.isEnabled = false

        user_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s != null && s.isEmpty()) {
                    join_chat.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.gray))
                    join_chat.isEnabled = false

                } else {
                    join_chat.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.colorAccent))
                    join_chat.isEnabled = true

                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        join_chat.setOnClickListener {
            if (join_chat.isEnabled) {
                disableLoginFields()
                login()

            }
        }
    }

    private fun disableLoginFields() {
        user_name.isEnabled = false
        join_chat.isEnabled = false

    }

    private fun enableLoginFields() {
        user_name.isEnabled = true
        join_chat.isEnabled = true

    }

    private fun subscribeNotification(userID: String) {
        val topic = "${getString(R.string.appID)}_${CometChatConstants.RECEIVER_TYPE_USER}_userID"

        FirebaseMessaging.getInstance().subscribeToTopic(topic)

    }

    private fun login() {
        val userID = user_name.text.toString()
        val apiKey = getString(R.string.apiKey)

        CometChat.login(userID, apiKey, object : CometChat.CallbackListener<User>() {
            override fun onSuccess(p0: User?) {
                val sharedPreferences = MySharedPreferences(applicationContext)

                sharedPreferences.login(userID)
                subscribeNotification(userID)
                user_name.setText("")
                enableLoginFields()
                showToast("Logged in as $userID")

                val intent = Intent(this@LoginActivity, MainActivity::class.java)

                startActivity(intent)
                finish()

            }

            override fun onError(p0: CometChatException?) {
                showToast("Username doesn't exist.")
                enableLoginFields()

            }
        })
    }
}
