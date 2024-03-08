package com.example.flixster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FlixsterViewAdapter (
    private val movies: List<Movie>,
    private val mListener: OnListFragmentInteractionListener? = null
    )
    : RecyclerView.Adapter<FlixsterViewAdapter.MovieViewHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_flixster, parent, false)
            return MovieViewHolder(view)
        }

        /**
         * This inner class lets us refer to all the different View elements
         * (Yes, the same ones as in the XML layout files!)
         */
        inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
            var mItem: Movie? = null
            val mMovieTitle: TextView = mView.findViewById<View>(R.id.tv_title) as TextView
            val mMovieImage: ImageView = mView.findViewById<View>(R.id.img_poster) as ImageView
            val mMovieRating: RatingBar = mView.findViewById<RatingBar>(R.id.rating_movie)
        }

        /**
         * This lets us "bind" each Views in the ViewHolder to its' actual data!
         */
        override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
            val movie = movies[position]

            holder.mItem = movie
            holder.mMovieTitle.text = movie.title
            val rating = movie.rating?.div(2)
            if (rating != null) {
                holder.mMovieRating.rating = rating
            }

            holder.mView.setOnClickListener {
                holder.mItem?.let { movie ->
                    mListener?.onItemClick(movie)
                }
            }

            Glide.with(holder.mView)
                .load("https://image.tmdb.org/t/p/w500" + movie.poster)
                .centerInside()
                .into(holder.mMovieImage)
        }

        /**
         * Remember: RecyclerView adapters require a getItemCount() method.
         */
        override fun getItemCount(): Int {
            return movies.size
        }
}