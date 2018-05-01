package com.project.starwars.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.project.starwars.R
import com.project.starwars.listener.ItemClickListener
import com.project.starwars.model.StarWarsSearchData

class SearchResultAdapter(results: List<StarWarsSearchData>, listener: ItemClickListener):
        RecyclerView.Adapter<SearchResultViewHolder>() {

    private val mResultsList = results
    // item click listener
    private val mListener = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.search_item, parent, false)
        return SearchResultViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mResultsList.size
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val starWarsData = mResultsList[position]
        holder.bind(starWarsData, mListener)
    }
}