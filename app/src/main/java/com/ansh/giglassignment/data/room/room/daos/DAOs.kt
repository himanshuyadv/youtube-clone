package com.ansh.giglassignment.data.room.room.daos

import androidx.room.*
import com.ansh.giglassignment.data.room.room.entities.timeline.TimelineEntity

@Dao
interface TimelineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimelineItem(entity: TimelineEntity)

    @Update
    suspend fun updateTimelineItem(entity: TimelineEntity)

    @Delete
    suspend fun deleteTimelineItem(entity: TimelineEntity)

    @Query("SELECT * FROM timeline_list")
    suspend fun getAllTimelineItems(): List<TimelineEntity>

    @Query("DELETE FROM timeline_list")
    suspend fun deleteAllTimelineItems()

}