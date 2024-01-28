package com.ansh.giglassignment.di

import android.content.Context
import com.ansh.giglassignment.data.room.room.GiglDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): GiglDatabase {
        return GiglDatabase.getDatabase(context)
    }

}