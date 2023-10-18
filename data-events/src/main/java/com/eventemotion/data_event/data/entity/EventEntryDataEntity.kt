package com.eventemotion.data_event.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventEntryDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = -1,
    val name: String,
    val feeling: String,
    val thought: String
)
