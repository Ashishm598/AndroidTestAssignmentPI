package com.assignment.shadiandroidtest.services

import com.assignment.shadiandroidtest.app.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {

    @GET(Constants.API_URL)
    fun getResults(@Query("results") resultLimit: Int? = 0)

}