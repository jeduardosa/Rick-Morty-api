package com.omeghabit.rickandmortyapi.repository

import com.omeghabit.rickandmortyapi.model.adapter.Character

class CharacterRepository {

    private val apiService = ApiClient.apiService

    suspend fun getCharacters(): List<Character> {
        return try {
            apiService.getCharacters().results
        } catch (e: Exception) {
            emptyList()
        }
    }
}