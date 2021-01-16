package com.example.pruebaanchorbooks

import android.content.Intent
import android.content.res.Resources.getSystem
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pruebaanchorbooks.model.room.RoomBookDetails
import com.example.pruebaanchorbooks.viewmodel.BooksViewModel
import com.squareup.picasso.Picasso

class BookDetailsFragment(var bookId:Int): Fragment(), View.OnClickListener {

    private lateinit var vModel:BooksViewModel
    private lateinit var image:ImageView
    private lateinit var title:TextView
    private lateinit var autor:TextView
    private lateinit var country:TextView
    private lateinit var lang:TextView
    private lateinit var year:TextView
    private lateinit var price:TextView
    private lateinit var last:TextView
    private lateinit var pages:TextView
    private lateinit var deliv:ImageView
    private lateinit var link:TextView
    private lateinit var detail:RoomBookDetails
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vModel = ViewModelProvider(this).get(BooksViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.book_details_fragment, container, false)
        image = view.findViewById(R.id.det_image)
        title = view.findViewById(R.id.det_title)
        autor = view.findViewById(R.id.det_author)
        country = view.findViewById(R.id.det_country)
        lang = view.findViewById(R.id.det_language)
        year = view.findViewById(R.id.det_year)
        price = view.findViewById(R.id.det_price)
        last = view.findViewById(R.id.det_last)
        pages = view.findViewById(R.id.det_pages)
        deliv = view.findViewById(R.id.det_delivery)
        link = view.findViewById(R.id.det_link)
        button = view.findViewById(R.id.button)
        button.setOnClickListener(this);
        vModel.getDetails(bookId)

        vModel.bookDetails.observe(context as MainActivity, Observer { if(it != null && !it.title.isNullOrEmpty()){
            title.text = it.title
            autor.text = getSystem().getString(R.string.author, it.author)
            country.text = getSystem().getString(R.string.country, it.country)
            lang.text = getSystem().getString(R.string.language, it.language)
            Picasso.get().load(it.imageLink).into(image)
            link.text = getSystem().getString(R.string.link, it.link)
            year.text = getSystem().getString(R.string.year, it.year)
            pages.text = getSystem().getString(R.string.pages, it.pages)
            price.text = getSystem().getString(R.string.price, it.price)
            last.text = getSystem().getString(R.string.last, it.lastPrice)
            if(it.delivery)
                deliv.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ok_mark, context?.theme))
            else
                deliv.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.bad_mark, context?.theme))
            detail = it
        }
        })

        return view
    }

    //Envia Email
    fun sendEmail(){
        val inten: Intent = Intent(Intent.ACTION_SEND).apply {
            setData(Uri.parse("mailto:"))
            setType("text/plain")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("ventas@anchorBooks.cl")) // recipients
            putExtra(Intent.EXTRA_SUBJECT, "Consulta por libro ${detail.title} id ${detail.id}")
            putExtra(
                Intent.EXTRA_TEXT, "Hola\n\n" +
                    "Vi el libro ${detail.title} y me gustaría que me contactaran a este correo o al\n" +
                    "siguiente número _________\n" +
                    "Quedo atento.")
        }

        startActivity(inten)
    }

    override fun onClick(v: View?) {
        sendEmail()
    }
}