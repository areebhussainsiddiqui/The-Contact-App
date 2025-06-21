package com.ahs.contactapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahs.contactapp.repository.ContactRepository
import com.ahs.contactapp.room.entity.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ContactRepository) : ViewModel() {


    val getContacts: LiveData<List<Contact>> = repository.getAllContacts()

     fun addContact(contact: Contact) {
        viewModelScope.launch {
            repository.insert(contact)
        }
    }


    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            repository.delete(contact)
        }
    }


}