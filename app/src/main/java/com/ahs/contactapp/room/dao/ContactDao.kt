package com.ahs.contactapp.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ahs.contactapp.room.entity.Contact

@Dao
interface ContactDao {

    @Insert
    suspend fun insert(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Query("SELECT * FROM contact")
    fun getAllContacts(): LiveData<List<Contact>>
}