package com.assignment.shadiandroidtest.services

import com.assignment.shadiandroidtest.app.Constants
import com.assignment.shadiandroidtest.models.MainResponseModel
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {

    @GET(Constants.API_URL)
    fun getResults(@Query("results") resultLimit: Int? = 0) : Observable<Response<MainResponseModel?>?>

}