package com.example.finalapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ObjectViewModelFactory(private val repository: ObjectRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ObjectViewModel(repository) as T
    }
}
