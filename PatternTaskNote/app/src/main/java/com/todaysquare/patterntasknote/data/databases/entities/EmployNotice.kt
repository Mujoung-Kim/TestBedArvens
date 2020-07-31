package com.todaysquare.patterntasknote.data.databases.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employ")
data class EmployNotice(
    val num: String,
    val title: String,
    @PrimaryKey
    var link: String

)