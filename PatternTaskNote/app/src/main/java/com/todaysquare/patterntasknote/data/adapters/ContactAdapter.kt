package com.todaysquare.patterntasknote.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.todaysquare.patterntasknote.R
import com.todaysquare.patterntasknote.data.databases.entities.Contact

import kotlinx.android.synthetic.main.item_contact.view.*

/*
    * RecyclerView 와 activity 간의 데이터 연결 -> activity/fragment 에 적용 가능
*/
class ContactAdapter(val contactItemClick: (Contact) -> Unit,
    val contactItemLongClick: (Contact) -> Unit) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    private var contacts = listOf<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)

        return ViewHolder(view)

    }

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contacts[position])

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(contact: Contact) {
            itemView.item_tv_name.text = contact.name
            itemView.item_tv_number.text = contact.number
            itemView.item_tv_initial.text = contact.initial.toString()

            itemView.setOnClickListener { contactItemClick(contact) }
            itemView.setOnLongClickListener {
                contactItemLongClick(contact)
                true

            }
        }
    }

    fun setContacts(contacts: List<Contact>) {
        this.contacts = contacts
        notifyDataSetChanged()

    }
}