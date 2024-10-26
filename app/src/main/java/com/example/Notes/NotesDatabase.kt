package com.example.Notes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [NotesData::class],  version = 1 /* version of our data base*/)
abstract class NotesDatabase : RoomDatabase(){
    abstract fun getNotesDao():NotesDao
    companion object{
        fun createDatabase(context : Context) = /*the codes are return value of this function*/
            Room.databaseBuilder(context.applicationContext, NotesDatabase::class.java, "NotesDB.db")
                .allowMainThreadQueries()
                .build()

    }

}