package com.todaysquare.patterntasknote.data.databases.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class Contact (
    //  insert, delete, update 모두 primaryKey 값을 기준으로 하기 때문에 되도록이면 autoGenerate 는 피하는게 좋다.
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "number")
    var number: String,
    @ColumnInfo(name = "initial")
    var initial: Char

) {
    constructor() : this(null, "", "", '\u0000')

}