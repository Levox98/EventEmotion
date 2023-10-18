package com.eventemotion.data_event.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eventemotion.data_event.data.entity.EventEntryDataEntity
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface EventsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEventEntry(eventEntryDataEntity: EventEntryDataEntity)

    @Query("SELECT * FROM events ORDER BY date DESC")
    suspend fun getEventEntriesFlow(): Flow<List<EventEntryDataEntity>>

    @Query("DELETE FROM events WHERE date = :date")
    suspend fun deleteEventEntry(date: Date)
}