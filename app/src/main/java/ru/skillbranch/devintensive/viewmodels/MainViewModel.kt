package ru.skillbranch.devintensive.viewmodels

import ru.skillbranch.devintensive.repositories.ChatRepository

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val chatRepository = ChatRepository
}