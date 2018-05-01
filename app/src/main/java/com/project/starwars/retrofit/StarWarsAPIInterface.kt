package com.project.starwars.retrofit

import com.project.starwars.model.StarWarsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsAPIInterface {

    @GET("people/?")
    fun getPeople(@Query("search") search: String): Single<StarWarsResponse>
}