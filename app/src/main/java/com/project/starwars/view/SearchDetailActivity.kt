package com.project.starwars.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.project.starwars.R
import com.project.starwars.model.StarWarsSearchData
import kotlinx.android.synthetic.main.activity_search_detail.*

// Detail screen
class SearchDetailActivity: AppCompatActivity() {

    companion object {
        const val TAG: String = "SearchDetailActivity"
    }
    private lateinit var starWarsData: StarWarsSearchData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_detail)
        // unwrap data from the bundle
        val bundle = intent.extras
        if (bundle != null) {
            starWarsData = bundle.getSerializable(SearchResultActivity.STARWARS_DATA) as StarWarsSearchData
            // display the values
            search_detail_name.text = starWarsData.name
            search_detail_gender_tv.text = starWarsData.gender
            search_detail_birth_tv.text = starWarsData.birthYear
            search_detail_eye_tv.text = starWarsData.eyeColor
            search_detail_hair_tv.text = starWarsData.hairColor
            search_detail_height_tv.text = starWarsData.height
            search_detail_skin_tv.text = starWarsData.skinColor
            search_detail_mass_tv.text = starWarsData.mass
        } else {
            Log.e(TAG, "data is null")
            // display error using a snack bar. We can also add an action to this, if required later.
            Snackbar.make(findViewById<View>(android.R.id.content), R.string.error_message, Snackbar.LENGTH_LONG).show()
        }
    }
}