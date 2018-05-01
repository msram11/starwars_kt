package com.project.starwars.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.project.starwars.listener.ItemClickListener
import com.project.starwars.model.StarWarsSearchData
import kotlinx.android.synthetic.main.search_item.view.*

class SearchResultViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    // binds the data with the view
    fun bind(searchData: StarWarsSearchData, listener: ItemClickListener) {
        // set the data to our view (name & gender)
        itemView.search_item_name.text = searchData.name
        itemView.search_item_gender.text = searchData.gender
        // item click listener
        itemView.setOnClickListener {
            listener.onItemClick(searchData)
        }
    }
}