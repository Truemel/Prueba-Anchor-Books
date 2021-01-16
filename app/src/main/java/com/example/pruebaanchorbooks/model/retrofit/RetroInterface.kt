package com.example.pruebaanchorbooks.model.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetroInterface {
    @GET("books")
    fun getBooksList():Call<MutableList<RetroBook>>

    @GET("bookDetail/{id}")
    fun getBookDetails(@Path("id") id:Int):Call<RetroBookDetails>
}