package com.example.flixster

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "DetailActivity"
class DetailActivity : AppCompatActivity() {
    private lateinit var detailImageView: ImageView
    private lateinit var detailTitleTextView: TextView
    private lateinit var descriptionTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // TODO: Find the views for the screen
        detailImageView = findViewById(R.id.iv_detailImage)
        detailTitleTextView = findViewById(R.id.tv_detailTitle)
        descriptionTextView = findViewById(R.id.tv_detailSummary)

        // TODO: Get the extra from the Intent
        val movie = intent.getSerializableExtra("movie") as Movie

        // TODO: Set the title, byline, and abstract information from the article
        detailTitleTextView.text = movie.title
        descriptionTextView.text = movie.overview

        // TODO: Load the media image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + movie.poster)
            .centerInside()
            .into(detailImageView)
    }
}