package com.example.pruebaanchorbooks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebaanchorbooks.model.room.RoomBook
import com.squareup.picasso.Picasso

class BooksListAdapter(var list:MutableList<RoomBook>, private var context:Context) :RecyclerView.Adapter<BooksListAdapter.Holder>(),
    View.OnClickListener {


    fun update(list:MutableList<RoomBook>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var image:ImageView
        lateinit var title:TextView
        lateinit var autor:TextView
        lateinit var country:TextView
        lateinit var lang:TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.books_list_adapter_layout, parent, false)
        val holder:Holder = Holder(view)
        holder.image = view.findViewById(R.id.book_image)
        holder.title = view.findViewById(R.id.book_title)
        holder.autor = view.findViewById(R.id.book_author)
        holder.country = view.findViewById(R.id.book_country)
        holder.lang = view.findViewById(R.id.book_language)
        view.setOnClickListener(this)

        return holder
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.tag = list[position].id
        holder.title.text = list[position].title
        holder.autor.text = context.getResources().getString(R.string.author, list[position].author)
        holder.country.text = context.getResources().getString(R.string.country, list[position].country)
        holder.lang.text = context.getResources().getString(R.string.language, list[position].language)
        Picasso.get().load(list[position].imageLink).into(holder.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onClick(v: View?) {
        (context as MainActivity).changeFragment(BookDetailsFragment(v!!.tag as Int))
    }
}