package com.todaysquare.patterntasknote.ui.contact

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import com.todaysquare.patterntasknote.data.databases.entities.Contact
import com.todaysquare.patterntasknote.data.repositories.ContactRepository

/*
    * Room database 의 인스턴스를 만들 때에는 context 가 필요하므로, AndroidViewModel 을 호출한다.
    * 하지만 viewModel 이 activity 의 context 를 사용하게 되면, 화면 전환 등의 이유로 충돌이 발생할 수 있다.
    * 데이터를 제어할 수 있는 함수는 Repository 에 함수를 만들고 사용한다.

*/
class ContactViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ContactRepository(application)
    private val contacts = repository.getAll()

    fun getAll(): LiveData<List<Contact>> = this.contacts

    fun insert(contact: Contact) {
        repository.insert(contact)

    }

    fun delete(contact: Contact) {
        repository.delete(contact)

    }
}