package com.bcafinance.omdb.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.item_movie.view.*

class OMDBViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val movieName = view.txtMovieName
    val director = view.txtDirector
    val rating = view.txtRating
    val image = view.photo
    val favourite = view.btnFavourite

    fun bindData(adapter: OMDBAdapter, position: Int) {

        movieName.setText(adapter.data.get(position)?.title)
        director.setText(adapter.data.get(position)?.year)
        rating.setText(adapter.data.get(position)?.type)

        image?.let {
            Glide.with(adapter.parent.context)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .load(adapter.data.get(position)?.poster)
                .into(it)

        }
    }
}