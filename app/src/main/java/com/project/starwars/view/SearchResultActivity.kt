package com.project.starwars.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.project.starwars.R
import com.project.starwars.adapter.SearchResultAdapter
import com.project.starwars.listener.ItemClickListener
import com.project.starwars.model.StarWarsResponse
import com.project.starwars.model.StarWarsSearchData
import com.project.starwars.viewmodel.SearchListViewModel
import kotlinx.android.synthetic.main.activity_search_result.*

// Result List screen
class SearchResultActivity : AppCompatActivity(), ItemClickListener {

    companion object {
        const val TAG: String = "SearchResultActivity"
        const val STARWARS_DATA: String = "starwars_data"
    }
    // search paramater value
    private lateinit var searchValue: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
        // initialize recycler view
        results_rv.layoutManager = LinearLayoutManager(this)
        results_rv.itemAnimator = DefaultItemAnimator()
        // add default item divider
        val mDividerItemDecoration = DividerItemDecoration(results_rv.context, DividerItemDecoration.VERTICAL)
        results_rv.addItemDecoration(mDividerItemDecoration)
        // retrieve the search value from the bundle
        val bundle: Bundle = intent.extras
        if (!bundle.isEmpty) {
            searchValue = bundle.getString(SearchActivity.SEARCH_VALUE)
        }
        // create the view model instance
        val viewModel = ViewModelProviders.of(this).get(SearchListViewModel::class.java)
        // retrieve the list of earthquakes - begin observing to the view model
        viewModel.retrieveData(searchValue).observe(this, searchResultObserver)
    }

    private var searchResultObserver: Observer<StarWarsResponse> = Observer { starWarResponse ->
        // send the result list to the recycler view adapter for display
        if (starWarResponse != null && starWarResponse.results.isNotEmpty()) {
            // set the result list to our adapter
            Log.d(TAG, "calling the recycler view adapter with result count -> ${starWarResponse.results.size}")
            results_rv.adapter = SearchResultAdapter(starWarResponse.results, this@SearchResultActivity)
        } else {
            // API call has failed, display an error message to the user
            Log.e(TAG, "search response is null or empty")
            // display error using a snack bar. We can also add an action to this, if required later.
            Snackbar.make(findViewById<View>(android.R.id.content), R.string.error_message, Snackbar.LENGTH_LONG).show()
        }
    }

    // recycler view click listener
    override fun onItemClick(data: StarWarsSearchData) {
        // invoke details screen and pass the data
        Log.d(TAG, "Item click event")
        // bundle the data and pass it to the detail screen
        val bundle = Bundle()
        bundle.putSerializable(STARWARS_DATA, data)
        val intent = Intent(this, SearchDetailActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}