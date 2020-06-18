package com.todaysquare.restapiexample.ui.activities

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Window

import com.todaysquare.restapiexample.R
import com.todaysquare.restapiexample.data.network.CometChatAPI
import com.todaysquare.restapiexample.data.preferences.MySharedPreferences

import kotlinx.android.synthetic.main.activity_add_friend_dialog.*

class AddFriendDialogActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_add_friend_dialog)

        add_friend_btn.isEnabled = false

        add_friend_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                add_friend_btn.isEnabled = !(s != null && s.isEmpty())

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        add_friend_btn.setOnClickListener {
            if (add_friend_btn.isEnabled)
                addFriend()

        }
    }

    private fun addFriend() {
        val api = CometChatAPI(applicationContext)

        api.addFriend(MySharedPreferences(applicationContext).getUID()!!, add_friend_text.text.toString(), this)

    }
}
