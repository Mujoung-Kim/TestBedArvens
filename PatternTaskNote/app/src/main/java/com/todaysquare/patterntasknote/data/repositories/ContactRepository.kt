package com.todaysquare.patterntasknote.data.repositories

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData

import com.todaysquare.patterntasknote.data.databases.ContactDatabase
import com.todaysquare.patterntasknote.data.databases.entities.Contact

import java.io.IOException

/*
    * Repository 구현 시 필요한 부분
        1. viewModel 에서 DB 접근 시 요청할 함수를 구현 -> DB, viewModel 의 독립성 보장
        2. viewModel 에서 DB 접근 시 mainThread 에서 접근할 시 충돌이 발생하므로 backgroundThread 로 구현

*/
class ContactRepository(val application: Application) {
    private val contactDatabase = ContactDatabase.getInstance(application)!!
    private val contactDao = contactDatabase.contactDao()
    private val contacts = contactDao.getAll()

    fun getAll(): LiveData<List<Contact>> = contacts

    fun insert(contact: Contact) {
        try {
            val thread = Thread(Runnable { contactDao.insert(contact) })

            thread.start()

        } catch (error: IOException) {
            error.printStackTrace()
            Toast.makeText(application, error.message, Toast.LENGTH_LONG).show()

        }
    }

    fun delete(contact: Contact) {
        try {
            val thread = Thread(Runnable { contactDao.delete(contact) })

            thread.start()

        } catch (error: IOException) {
            error.printStackTrace()
            Toast.makeText(application, error.message, Toast.LENGTH_LONG).show()

        }
    }
}