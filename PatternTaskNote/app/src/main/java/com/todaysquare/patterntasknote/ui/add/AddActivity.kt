package com.todaysquare.patterntasknote.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels

import com.todaysquare.patterntasknote.R
import com.todaysquare.patterntasknote.data.databases.entities.Contact
import com.todaysquare.patterntasknote.ui.ContactViewModel
import com.todaysquare.patterntasknote.utils.Constants.Param.Companion.EXTRA_CONTACT_ID
import com.todaysquare.patterntasknote.utils.Constants.Param.Companion.EXTRA_CONTACT_NAME
import com.todaysquare.patterntasknote.utils.Constants.Param.Companion.EXTRA_CONTACT_NUMBER

import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    private val contactViewModel: ContactViewModel by viewModels()
    private var id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        if (intent != null && intent.hasExtra(EXTRA_CONTACT_NAME) && intent.hasExtra(EXTRA_CONTACT_NUMBER)
            && intent.hasExtra(EXTRA_CONTACT_ID)) {

            add_edit_name.setText(intent.getStringExtra(EXTRA_CONTACT_NAME))
            add_edit_number.setText(intent.getStringExtra(EXTRA_CONTACT_NUMBER))

            id = intent.getLongExtra(EXTRA_CONTACT_ID, -1)

        }

        add_button.setOnClickListener {
            val name = add_edit_name.text.toString().trim()
            val number = add_edit_number.text.toString()

            if (name.isEmpty() || number.isEmpty())
                Toast.makeText(this, "Please enter name and number", Toast.LENGTH_SHORT).show()
            else {
                val initial = name[0].toUpperCase()
                val contact = Contact(id, name, number, initial)

                contactViewModel.insert(contact)
                finish()

            }
        }
    }
}