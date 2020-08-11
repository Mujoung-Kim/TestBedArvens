package com.todaysquare.functiontest.ui.charaterdetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap

import com.todaysquare.functiontest.data.repositores.CharacterRepository

class CharacterDetailViewModel @ViewModelInject constructor(private val repository: CharacterRepository)
    : ViewModel() {

    private val _id = MutableLiveData<Int>()
    private val _character = _id.switchMap { id ->
        repository.getCharacter(id)

    }
    val character = _character

    fun start(id: Int) {
        _id.value = id

    }
}