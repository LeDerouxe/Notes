package com.example.Notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notha") // annotate "@Entity(put the name of table)" before a data class means that our dataClass is our table
data class NotesData(
    @PrimaryKey(autoGenerate = true) //the id column
    var id : Int = 0,
    @ColumnInfo(name = "title") // normal columns
    var a : String,
    @ColumnInfo(name = "des")
    var b : String
    ){}