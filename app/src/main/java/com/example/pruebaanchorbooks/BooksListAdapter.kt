package com.example.pruebaanchorbooks

import android.content.Context
import android.content.res.Resources.getSystem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebaanchorbooks.model.room.RoomBook
import com.squareup.picasso.Picasso

class BooksListAdapter(var list:MutableList<RoomBook>, var context:Context) :RecyclerView.Adapter<BooksListAdapter.Holder>(),
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
        holder.itemView.tag = list.get(position).id
        holder.title.text = list.get(position).title
        holder.autor.text = getSystem().getString(R.string.author, list.get(position).author)
        holder.country.text = getSystem().getString(R.string.country, list.get(position).country)
        holder.lang.text = getSystem().getString(R.string.language, list.get(position).language)
        Picasso.get().load(list.get(position).imageLink).into(holder.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onClick(v: View?) {
        (context as MainActivity).changeFragment(BookDetailsFragment(v!!.tag as Int))
    }
}