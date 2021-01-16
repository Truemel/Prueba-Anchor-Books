package com.example.pruebaanchorbooks.model.retrofit

import retrofit2.Call
import retrofit2.Callback

class RetroRequests {

    private lateinit var onRetroListener:RetroInterface

    init {
        onRetroListener = RetrofitClient.getRetro().create(RetroInterface::class.java)
    }

    //Request para conseguir la lista de libros
    fun getBooksList(callback: Callback<MutableList<RetroBook>>){
        val call:Call<MutableList<RetroBook>> = onRetroListener.getBooksList()
        call.enqueue(callback)
    }

    //Request para conseguir detalles del libro
    fun getBookDetails(id:Int, callback: Callback<RetroBookDetails>){
        val call:Call<RetroBookDetails> = onRetroListener.getBookDetails(id)
        call.enqueue(callback)
    }
}