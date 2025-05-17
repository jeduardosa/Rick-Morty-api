package com.omeghabit.rickandmortyapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omeghabit.rickandmortyapi.constants.DataBaseConstants
import com.omeghabit.rickandmortyapi.model.adapter.Character
import com.omeghabit.rickandmortyapi.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharacterViewModel: ViewModel() {

    private val repository = CharacterRepository()
    val characters = MutableLiveData<List<Character>>()
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun fetchCharacters() {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val result = repository.getCharacters()
                characters.postValue(result)
                error.postValue("")
            } catch (e: Exception) {
                error.postValue(DataBaseConstants.MENSSAGE.FAIL)
            } finally {
                isLoading.postValue(false)
            }
        }
    }
}