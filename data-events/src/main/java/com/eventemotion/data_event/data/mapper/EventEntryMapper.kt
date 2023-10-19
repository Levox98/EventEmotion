package com.eventemotion.data_event.data.mapper

import com.eventemotion.data_event.data.entity.EventEntryDataEntity
import com.eventemotion.data_event.domain.entity.EventEntry

fun EventEntry.toData() = EventEntryDataEntity(name, feeling, thought, date)

fun EventEntryDataEntity.toDomain() = EventEntry(name, feeling, thought, date)