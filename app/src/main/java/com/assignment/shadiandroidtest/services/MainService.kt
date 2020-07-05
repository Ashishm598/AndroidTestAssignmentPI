package com.assignment.shadiandroidtest.services

import com.assignment.shadiandroidtest.constants.Constants
import com.assignment.shadiandroidtest.models.MainResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {

    @GET(Constants.API_URL)
    fun getResults(@Query("results") resultLimit: Int? = 0) : Observable<Response<MainResponse?>?>

}