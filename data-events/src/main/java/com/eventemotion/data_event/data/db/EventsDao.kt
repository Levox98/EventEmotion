package com.eventemotion.data_event.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eventemotion.data_event.data.entity.EventEntryDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addEventEntry(eventEntryDataEntity: EventEntryDataEntity)

    @Query("SELECT * FROM events")
    fun getEventEntriesFlow(): Flow<List<EventEntryDataEntity>>

    @Query("DELETE FROM events WHERE date = :date")
    fun deleteEventEntry(date: Long)
}