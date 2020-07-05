package com.assignment.shadiandroidtest.entities.user

import com.assignment.shadiandroidtest.constants.Status

data class UserStatus(var voted: Boolean = false, var status: Status? = null)