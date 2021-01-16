package com.example.pruebaanchorbooks.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RoomBook::class, RoomBookDetails::class], version = 1, exportSchema = false)
abstract class BooksRoomDataBase:RoomDatabase() {

    abstract fun getDAO():RoomDAO

    companion object{
        private const val DB_NAME = "anchor_books_db"
        private var db:BooksRoomDataBase? = null

        fun getDB(context: Context):BooksRoomDataBase{
            if(db == null)
                synchronized(this){
                    db = Room.databaseBuilder(context, BooksRoomDataBase::class.java, DB_NAME).build()
                }
            return db!!
        }
    }
}