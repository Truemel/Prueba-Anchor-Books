package com.example.pruebaanchorbooks

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebaanchorbooks.model.room.RoomBook

class BooksListAdapter(var list:MutableList<RoomBook>) :RecyclerView.Adapter<BooksListAdapter.Holder>(){


    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return list.size
    }
}