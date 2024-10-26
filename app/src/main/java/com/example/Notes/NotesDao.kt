package com.example.Notes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao // Data Assest Object
interface NotesDao {

    @Insert    // READ
    fun insertItem( notesData: NotesData)

   @Delete
     fun deleteItem (notesData: NotesData)

    @Query("SELECT * FROM notha" /*WRITE*/) //for any query command actually
    fun getAllItems (): MutableList<NotesData>

    @Update
    fun updateItem ( notesData: NotesData)

}