package com.example.pruebaanchorbooks.model.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RoomDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooksList(list: MutableList<RoomBook>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookDetails(details: RoomBookDetails)

    @Update
    suspend fun updateBook(book: RoomBook)

    @Update
    suspend fun updateBookDetails(details: RoomBookDetails)

    @Query("SELECT * FROM books_table")
    fun getBooksList():LiveData<MutableList<RoomBook>>

    @Query("SELECT * FROM books_details_table WHERE `id`=:id")
    fun getBookDetails(id:Int):LiveData<RoomBookDetails>
}