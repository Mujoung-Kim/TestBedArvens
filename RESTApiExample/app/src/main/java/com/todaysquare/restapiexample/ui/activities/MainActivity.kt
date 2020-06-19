package com.todaysquare.restapiexample.ui.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment

import com.cometchat.pro.constants.CometChatConstants
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging
import com.todaysquare.restapiexample.R
import com.todaysquare.restapiexample.data.preferences.MySharedPreferences
import com.todaysquare.restapiexample.ui.fragments.ChatsFragment
import com.todaysquare.restapiexample.ui.fragments.ContactsFragment
import com.todaysquare.restapiexample.utils.showToast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
        private const val ADD_FRIEND_REQUEST_CODE = 0

    }
    private lateinit var activeFragment: Fragment
    private var chatsFragment = ChatsFragment()
    private var contactsFragment = ContactsFragment()
    private lateinit var sharedPreferences: MySharedPreferences

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_contacts -> {
                supportFragmentManager.beginTransaction().hide(activeFragment)
                    .show(contactsFragment).commit()
                activeFragment = contactsFragment

                if (supportActionBar != null)
                    supportActionBar!!.title = getString(R.string.title_contacts)
                return@OnNavigationItemSelectedListener true

            }

            R.id.navigation_chats -> {
                supportFragmentManager.beginTransaction().hide(activeFragment)
                    .show(chatsFragment).commit()
                activeFragment = chatsFragment

                if (supportActionBar != null)
                    supportActionBar!!.title = getString(R.string.title_chats)
                return@OnNavigationItemSelectedListener true

            }
        }
        false

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportActionBar != null)
            supportActionBar!!.title = getString(R.string.title_contacts)

        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(main_container.id, chatsFragment)
            .hide(chatsFragment).commit()
        supportFragmentManager.beginTransaction().add(main_container.id, contactsFragment).commit()
        activeFragment = contactsFragment

        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        sharedPreferences = MySharedPreferences(this)
        if (!sharedPreferences.isLoggedIn()) startLoginActivity()

    }

    private fun startLoginActivity() {
        val intent = Intent(this@MainActivity, LoginActivity::class.java)

        startActivity(intent)
        finish()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater

        inflater.inflate(R.menu.friends_appbar_menu, menu)

        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item == null) return super.onOptionsItemSelected(item)

        return when (item.itemId) {
            R.id.friends_action_logout -> {
                sharedPreferences.logout()
                logoutCometChat()
                true

            }
            R.id.friends_action_add -> {
                val intent = Intent(this@MainActivity, AddFriendDialogActivity::class.java)

                startActivityForResult(intent, ADD_FRIEND_REQUEST_CODE)
                true

            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_FRIEND_REQUEST_CODE) {
            Log.d(TAG, "ResultCode : $resultCode")

            if (resultCode == Activity.RESULT_OK) refreshPage()

        }
    }

    private fun logoutCometChat() {
        unsubscribeNotification()
        CometChat.logout(object : CometChat.CallbackListener<String>() {
            override fun onSuccess(p0: String?) {
                Log.d(TAG, "Logout completed successfully")
                startLoginActivity()

            }

            override fun onError(p0: CometChatException?) {
                showToast("Logout failed")
                Log.d(TAG, "Logout failed with exception: ${p0?.message}")
                subscribeNotification()

            }
        })
    }

    private fun subscribeNotification() {
        val userID = CometChat.getLoggedInUser().uid
        val topic = "${getString(R.string.appID)}_${CometChatConstants.RECEIVER_TYPE_USER}_$userID"

        FirebaseMessaging.getInstance().subscribeToTopic(topic)
    }

    private fun unsubscribeNotification() {
        val userID = CometChat.getLoggedInUser().uid
        val topic = "${getString(R.string.appID)}_${CometChatConstants.RECEIVER_TYPE_USER}_$userID"

        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic)

    }

    private fun refreshPage() {
        finish()
        overridePendingTransition(0, 0)
        startActivity(intent)
        overridePendingTransition(0, 0)

    }
}
