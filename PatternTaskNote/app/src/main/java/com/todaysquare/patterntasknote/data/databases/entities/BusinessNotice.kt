package com.todaysquare.patterntasknote.data.databases.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "business")
data class BusinessNotice(
    val num: String,
    val title: String,
    @PrimaryKey
    val link: String

)