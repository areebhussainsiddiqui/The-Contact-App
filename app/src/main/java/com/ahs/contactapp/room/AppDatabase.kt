package com.ahs.contactapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahs.contactapp.room.dao.ContactDao
import com.ahs.contactapp.room.entity.Contact


@Database(entities = [Contact::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): ContactDao
}