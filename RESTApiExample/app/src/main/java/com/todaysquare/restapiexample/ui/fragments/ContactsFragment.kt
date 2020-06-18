package com.todaysquare.restapiexample.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.cometchat.pro.core.CometChat
import com.cometchat.pro.core.UsersRequest
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.User
import com.todaysquare.restapiexample.R
import com.todaysquare.restapiexample.ui.adapters.ContactsAdapter

import kotlinx.android.synthetic.main.fragment_contacts.*

class ContactsFragment : Fragment() {
    companion object {
        private const val TAG = "ContactsFragment"

    }
    private lateinit var contactsAdapter: ContactsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val viewInflater = inflater.inflate(R.layout.fragment_contacts, container, false)
        val layoutManager  = LinearLayoutManager(activity)

        contactsAdapter = ContactsAdapter(mutableListOf(), context!!)
        contacts.adapter = contactsAdapter
        getContactsList()

        return viewInflater

    }

    private fun getContactsList() {
        val usersRequest: UsersRequest?
        val limit = 100

        usersRequest = UsersRequest.UsersRequestBuilder()
            .setLimit(limit)
            .friendsOnly(true)
            .build()

        usersRequest?.fetchNext(object : CometChat.CallbackListener<List<User>>() {
            override fun onSuccess(contactsList: List<User>?) {
                if (contactsList != null) {
                    contactsAdapter.updateFriends(contactsList)
                    contactsAdapter.notifyDataSetChanged()
                    Log.d(TAG, "Retrieved list of online friends of ${contactsList.size}")

                } else Log.d(TAG, "Retrieved list of online friends, but is empty")

            }

            override fun onError(p0: CometChatException?) {
                Log.d(TAG, "Failed to load friends list with exception: $p0")

            }
        })
    }
}
