package com.assignment.shadiandroidtest.models

import com.assignment.shadiandroidtest.entities.user.UserEntity

data class MainResponseModel(var mainResponse: MainResponse?) {

    data class MainResponse(
        var info: Info? = null,
        var userEntities: MutableList<UserEntity>? = null
    )

    data class Info(
        var page: Int? = null,
        var results: Int? = null,
        var seed: String? = null,
        var version: String? = null
    )

}