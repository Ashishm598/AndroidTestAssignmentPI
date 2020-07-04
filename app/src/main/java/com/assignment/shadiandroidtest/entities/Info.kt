package com.assignment.shadiandroidtest.entities

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

data class Info(
    var page: Int? = null,
    var results: Int? = null,
    var seed: String? = null,
    var version: String? = null
)