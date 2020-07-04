package com.assignment.shadiandroidtest.entities.user

import com.assignment.shadiandroidtest.entities.converters.*
import com.assignment.shadiandroidtest.entities.user.*
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity

@Entity
data class UserEntity(
    @io.objectbox.annotation.Id
    var userId: Long = 0,
    var cell: String? = null,
    @Convert(converter = DobToStringConverter::class, dbType = String::class)
    var dob: Dob? = null,
    var email: String? = null,
    var gender: String? = null,
    @Convert(converter = IdToStringConverter::class, dbType = String::class)
    var id: Id? = null,
    @Convert(converter = LocationToStringConverter::class, dbType = String::class)
    var location: Location? = null,
    @Convert(converter = LoginToStringConverter::class, dbType = String::class)
    var login: Login? = null,
    @Convert(converter = NameToStringConverter::class, dbType = String::class)
    var name: Name? = null,
    var nat: String? = null,
    var phone: String? = null,
    @Convert(converter = PictureToStringConverter::class, dbType = String::class)
    var picture: Picture? = null,
    @Convert(converter = RegisteredToStringConverter::class, dbType = String::class)
    var registered: Registered? = null
)