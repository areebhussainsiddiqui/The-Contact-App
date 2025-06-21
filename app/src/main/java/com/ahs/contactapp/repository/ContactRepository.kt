package com.ahs.contactapp.repository

import androidx.lifecycle.LiveData
import com.ahs.contactapp.room.dao.ContactDao
import com.ahs.contactapp.room.entity.Contact
import javax.inject.Inject


class ContactRepository @Inject constructor(private val dao: ContactDao) {


    suspend fun insert(contact: Contact) {
        dao.insert(contact)
    }

    suspend fun delete(contact: Contact) {
        dao.delete(contact)
    }

    fun getAllContacts(): LiveData<List<Contact>> = dao.getAllContacts()
}