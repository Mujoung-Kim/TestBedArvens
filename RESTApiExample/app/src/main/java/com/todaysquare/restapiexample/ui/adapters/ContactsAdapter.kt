package com.todaysquare.restapiexample.ui.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.cometchat.pro.models.User
import com.todaysquare.restapiexample.R
import com.todaysquare.restapiexample.ui.activities.MessagesActivity

import kotlinx.android.synthetic.main.contact_item.view.*

class ContactsAdapter(private var friends: MutableList<User>, private val context: Context) :
    RecyclerView.Adapter<ContactsAdapter.FriendViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsAdapter.FriendViewHolder {
        val context = parent.context
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val viewInflater = inflater.inflate(R.layout.contact_item, parent, false)

        return FriendViewHolder(viewInflater)

    }

    override fun getItemCount(): Int = friends.size

    override fun onBindViewHolder(holder: ContactsAdapter.FriendViewHolder, position: Int) {
        holder.bind(friends[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(context, MessagesActivity::class.java)

            intent.putExtra("RECEIVER_ID", friends[position].uid)
            intent.putExtra("RECEIVER_NAME", friends[position].name)
            context.startActivity(intent)

        }
    }

    fun updateFriends(onlineFriends: List<User>) {
        this.friends = onlineFriends.toMutableList()

    }

    inner class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(friend: User) {
            itemView.friend_text.text = friend.name
            itemView.friend_status.text = friend.status

            if (friend.status == "online")
                itemView.friend_status.setTextColor(Color.GREEN)

        }
    }
}