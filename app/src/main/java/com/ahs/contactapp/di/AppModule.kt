package com.ahs.contactapp.di

import android.content.Context
import androidx.room.Room
import com.ahs.contactapp.repository.ContactRepository
import com.ahs.contactapp.room.AppDatabase
import com.ahs.contactapp.room.dao.ContactDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "post_database"
        ).build()
    }

    @Provides
    fun provideContactDao(appDatabase: AppDatabase): ContactDao {
        return appDatabase.postDao()
    }

    @Provides
    @Singleton
    fun providesContactRepository(contactDao: ContactDao): ContactRepository {
        return ContactRepository(contactDao)
    }
}