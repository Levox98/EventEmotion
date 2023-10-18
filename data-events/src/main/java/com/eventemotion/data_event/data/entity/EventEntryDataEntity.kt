package com.eventemotion.data_event.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "events")
data class EventEntryDataEntity(
    val name: String,
    val feeling: String,
    val thought: String,
    @PrimaryKey
    val date: Date
)
