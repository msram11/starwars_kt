package com.project.starwars.retrofit

class StarWarsAPIImplementation private constructor() {

    companion object {
        private lateinit var retrofitInterface: StarWarsAPIInterface

        fun createInterface(): StarWarsAPIInterface {

            retrofitInterface = StarWarsAPIClient.getClient().create(StarWarsAPIInterface::class.java)
            return retrofitInterface
        }
    }
}