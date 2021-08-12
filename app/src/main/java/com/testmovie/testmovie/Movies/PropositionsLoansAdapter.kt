package com.testmovie.testmovie.Movies

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.testmovie.testmovie.R
import com.testmovie.testmovie.data.local.model.Movie

class PropositionsLoansAdapter() :
    RecyclerView.Adapter<PropositionsLoansAdapter.ViewHolder>() {


    var list: MutableList<Movie> = arrayListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_movie, viewGroup, false)

        return ViewHolder(view)
    }

    fun setData(data: MutableList<Movie>?) {
        list.clear()
        if (data != null)
            list.addAll(data)
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val proposition = list[position]

        if (proposition.posterPath !== null){

         //   Log.d("picture_url",proposition.screen)

            Picasso.get().load(proposition.posterPath).into(viewHolder.image)}


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
      var name: TextView = view.findViewById(R.id.text_title)
      var image: ImageView = view.findViewById(R.id.image_movie_poster)

    }

    override fun getItemCount() = list.size

}