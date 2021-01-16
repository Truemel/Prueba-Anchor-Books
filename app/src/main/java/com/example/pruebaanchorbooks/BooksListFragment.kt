package com.example.pruebaanchorbooks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebaanchorbooks.viewmodel.BooksViewModel

class BooksListFragment : Fragment() {

    private lateinit var vModel:BooksViewModel
    private lateinit var adapter: BooksListAdapter
    private lateinit var listView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vModel =ViewModelProvider(this).get(BooksViewModel::class.java)
        adapter = BooksListAdapter(mutableListOf())
        vModel.booksList.observe(this, Observer { adapter.update(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.books_list_fragment_layout, container, false)
        listView = view.findViewById(R.id.recView)
        listView.layoutManager = LinearLayoutManager(context)
        listView.adapter = adapter

        return view
    }
}