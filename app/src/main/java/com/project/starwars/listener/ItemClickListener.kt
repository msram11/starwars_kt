package com.project.starwars.listener

import com.project.starwars.model.StarWarsSearchData

/*
    recycler view click listener
*/
interface ItemClickListener {

    fun onItemClick(data: StarWarsSearchData)
}