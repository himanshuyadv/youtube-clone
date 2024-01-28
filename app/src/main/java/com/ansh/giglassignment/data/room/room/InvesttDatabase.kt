package com.ansh.giglassignment.data.room.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ansh.giglassignment.data.room.room.daos.TimelineDao
import com.ansh.giglassignment.data.room.room.entities.timeline.TimelineEntity
import com.ansh.giglassignment.data.room.room.type_convertors.MyTypeConverters


@Database([TimelineEntity::class], version = 1)
@TypeConverters(MyTypeConverters::class)
abstract class GiglDatabase : RoomDatabase() {

    abstract fun getTimelineDao(): TimelineDao


    companion object {
        @Volatile
        private var INSTANCE: GiglDatabase? = null


        fun getDatabase(context: Context): GiglDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GiglDatabase::class.java,
                    context.packageName
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}