package com.example.pruebaanchorbooks.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books_details_table")
data class RoomBookDetails(@PrimaryKey val id:Int, val author:String, val country:String, val imageLink:String, val language:String, val title:String, val link:String, val pages:Int, val year:Int, val price:Int, val lastPrice:Int, val delivery:Boolean)
