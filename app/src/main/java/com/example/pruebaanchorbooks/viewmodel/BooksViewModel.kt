package com.example.pruebaanchorbooks.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.pruebaanchorbooks.model.retrofit.RetroBook
import com.example.pruebaanchorbooks.model.retrofit.RetroBookDetails
import com.example.pruebaanchorbooks.model.retrofit.RetroRequests
import com.example.pruebaanchorbooks.model.room.BooksRoomDataBase
import com.example.pruebaanchorbooks.model.room.RoomBook
import com.example.pruebaanchorbooks.model.room.RoomBookDetails
import com.example.pruebaanchorbooks.model.room.RoomDBManager
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel(application: Application) : AndroidViewModel(application) {

    private val dbManager:RoomDBManager
    val booksList:LiveData<MutableList<RoomBook>>
    lateinit var bookDetails:LiveData<RoomBookDetails>

    init {
        dbManager = RoomDBManager(BooksRoomDataBase.getDB(application).getDAO())
        booksList =dbManager.getBooksList()
        if(booksList.value.isNullOrEmpty())
            getBooksListRetro()
    }

    fun insertBooksList(list: MutableList<RetroBook>) = viewModelScope.launch { dbManager.insertBooksList(list) }
    fun insertBookDetails(det:RetroBookDetails) = viewModelScope.launch { dbManager.insertBookDetails(det) }
    fun updateBook(book:RetroBook) = viewModelScope.launch { dbManager.updateBook(book) }
    fun updateBookDetails(det:RetroBookDetails) = viewModelScope.launch { dbManager.updateBookDetails(det) }

    //obtiene los detalles de la base de datos, de lo contrario los obtiene de retrofit
    fun getDetails(id:Int){
        bookDetails = dbManager.getBookDetails(id)

        if(bookDetails.value == null || bookDetails.value!!.title.isEmpty())
            getBookDetailsRetro(id)
    }

    fun getBooksListRetro(){
        RetroRequests().getBooksList(object : Callback<MutableList<RetroBook>>{
            override fun onResponse(
                call: Call<MutableList<RetroBook>>,
                response: Response<MutableList<RetroBook>>
            ) {
                if(!response.body().isNullOrEmpty())
                    insertBooksList(response.body()!!)
            }

            override fun onFailure(call: Call<MutableList<RetroBook>>, t: Throwable) {
                Toast.makeText(getApplication(), "Error, couldn't get books list", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getBookDetailsRetro(id:Int){
        RetroRequests().getBookDetails(id, object :Callback<RetroBookDetails>{
            override fun onResponse(
                call: Call<RetroBookDetails>,
                response: Response<RetroBookDetails>
            ) {
                if(response.body() != null)
                    insertBookDetails(response.body()!!)
            }

            override fun onFailure(call: Call<RetroBookDetails>, t: Throwable) {
                Toast.makeText(getApplication(), "Error, couldn't get details", Toast.LENGTH_LONG).show()
            }
        })
    }
}