package com.project.starwars.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import com.project.starwars.R
import kotlinx.android.synthetic.main.activity_search.*

// Main Search Screen
class SearchActivity : AppCompatActivity() {

    companion object {
        const val TAG: String = "SearchActivity"
        const val SEARCH_VALUE: String = "search_value"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        // button click listener
        search_button.setOnClickListener({
            // search input field
            val searchText = search_text.text.toString()
            Log.d(TAG, "Button clicked!")
            if (searchText.isEmpty()) {
                // display error
                Log.e(TAG, "Search Text is Empty -> $searchText")
                Snackbar.make(findViewById<View>(android.R.id.content), R.string.search_empty_message, Snackbar.LENGTH_LONG).show()
            } else {
                Log.d(TAG, "searchText is -> $searchText")
                // redirect to next screen to display the search results
                val intent = Intent(this, SearchResultActivity::class.java)
                // To pass any data to next activity
                intent.putExtra(SEARCH_VALUE, searchText)
                // start your next activity
                startActivity(intent)
            }
        })
    }
}