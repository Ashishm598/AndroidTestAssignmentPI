package com.assignment.shadiandroidtest.entities

data class Login(
    var md5: String? = null,
    var password: String? = null,
    var salt: String? = null,
    var sha1: String? = null,
    var sha256: String? = null,
    var username: String? = null,
    var uuid: String? = null
)