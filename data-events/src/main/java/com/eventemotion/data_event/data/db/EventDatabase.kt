package com.eventemotion.data_event.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eventemotion.data_event.data.entity.EventEntryDataEntity

@Database(
    entities = [
        EventEntryDataEntity::class
    ],
    version = 1,
    exportSchema = false
)
internal abstract class EventDatabase : RoomDatabase() {
    abstract fun eventsDao(): EventsDao
}