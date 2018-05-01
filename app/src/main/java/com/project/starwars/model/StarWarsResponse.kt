package com.project.starwars.model

import com.google.gson.annotations.SerializedName
import kotlin.collections.ArrayList

class StarWarsResponse {

    @SerializedName("results")
    val results: List<StarWarsSearchData> = ArrayList()
}