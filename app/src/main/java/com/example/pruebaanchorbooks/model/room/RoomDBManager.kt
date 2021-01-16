package com.example.pruebaanchorbooks.model.room

import androidx.lifecycle.LiveData
import com.example.pruebaanchorbooks.model.retrofit.RetroBook
import com.example.pruebaanchorbooks.model.retrofit.RetroBookDetails

class RoomDBManager(val dao: RoomDAO) {

    //request para insertar la lista de libros a la base de datos
    suspend fun insertBooksList(list: MutableList<RetroBook>){
        val bookList:MutableList<RoomBook> = mutableListOf()
        for (book in list)
            bookList.add(RoomBook(book.id, book.author, book.country, book.imageLink, book.language, book.title))
        dao.insertBooksList(bookList)
    }

    //request para insertar los detalles de un libro a la base de datos
    suspend fun insertBookDetails(det:RetroBookDetails){
        dao.insertBookDetails(RoomBookDetails(det.id, det.author, det.country, det.imageLink, det.language, det.title, det.link, det.pages, det.year, det.price, det.lastPrice, det.delivery))
    }

    //request para actualizar un libro en la base de datos
    suspend fun updateBook(book:RetroBook){
        dao.updateBook(RoomBook(book.id, book.author, book.country, book.imageLink, book.language, book.title))
    }

    //request para actualizar los detalles de un libro en la base de datos
    suspend fun updateBookDetails(det: RetroBookDetails){
        dao.updateBookDetails(RoomBookDetails(det.id, det.author, det.country, det.imageLink, det.language, det.title, det.link, det.pages, det.year, det.price, det.lastPrice, det.delivery))
    }

    //request para obtener la lista de libros de la base de datos
    fun getBooksList(): LiveData<MutableList<RoomBook>> {
        return dao.getBooksList()
    }

    //request para obtener los detalles de un libro de la base de datos
    fun getBookDetails(id:Int): LiveData<RoomBookDetails> {
        return dao.getBookDetails(id)
    }
}