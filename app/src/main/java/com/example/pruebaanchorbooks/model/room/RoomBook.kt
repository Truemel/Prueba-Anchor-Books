package com.example.pruebaanchorbooks.model.room

import androidx.room.Entity

@Entity(tableName = "books_table")
data class RoomBook(val id:Int, val author:String, val country:String, val imageLink:String, val language:String, val title:String)
