package com.project.starwars.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.project.starwars.model.StarWarsResponse
import com.project.starwars.retrofit.StarWarsAPIImplementation
import com.project.starwars.retrofit.StarWarsAPIInterface
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchListViewModel: ViewModel() {

    companion object {
        const val TAG: String = "SearchListViewModel"
    }

    private var liveData: MutableLiveData<StarWarsResponse>? = null

    // retrieves the data - set up live data, fetch the data
    fun retrieveData(searchValue: String): MutableLiveData<StarWarsResponse> {
        // liveData is empty/null, proceed with fetching the data
        if (liveData == null) {
            Log.d(TAG, "live data is NULL")
            liveData = MutableLiveData()
            // create the API interface
            val apiInterface = StarWarsAPIImplementation.createInterface()
            // fetch the data from api call
            fetchStarWarsData(searchValue, apiInterface)
        }
        return liveData as MutableLiveData<StarWarsResponse>
    }

    // perform the retrofit call
    // TODO: we should move the api call to a service layer for better separation of concern
    private fun fetchStarWarsData(searchValue: String, apiInterface: StarWarsAPIInterface) {
        Log.d(TAG, "Begin fetch starwars data")
        val single: Single<StarWarsResponse> = apiInterface.getPeople(searchValue)
        single.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    starWarsResponse ->
                    liveData?.value = starWarsResponse
                }, {
                    throwable ->
                    Log.e(TAG, "fetchStarWarsData() -> onError(), exception -> " + throwable.localizedMessage)
                })
    }
}