package com.todaysquare.functiontest.ui.characters

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel

import com.todaysquare.functiontest.data.repositores.CharacterRepository

class CharactersViewModel @ViewModelInject constructor(
    private val repository: CharacterRepository) : ViewModel() {

    val characters = repository.getCharacters()

}