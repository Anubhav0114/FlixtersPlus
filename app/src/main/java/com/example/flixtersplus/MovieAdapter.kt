package com.example.flixtersplus

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val context : Context , private val list : List<Movies>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

         val image : ImageView = itemView.findViewById<ImageView>(R.id.poster_img)
         val title: TextView = itemView.findViewById<TextView>(R.id.tv_title)
         val overview : TextView = itemView.findViewById(R.id.tv_overview)
         val releaseDate : TextView = itemView.findViewById(R.id.release_date)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item , parent,false )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 8
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data =  list[position]
        val url =  "https://image.tmdb.org/t/p/w500" + data.imageUrl
        Glide.with(holder.image.context).load(url).into(holder.image)
        holder.title.text = data.title
        holder.overview.text = data.overview
        holder.releaseDate.text = data.releaseDate
    }
}