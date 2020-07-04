package com.assignment.shadiandroidtest.entities

import com.assignment.shadiandroidtest.entities.converters.InfoObjectToStringConverter
import com.assignment.shadiandroidtest.entities.converters.ResultsListToStringConverter
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class MainResponse(
    @Id var id: Long = 0,
    @Convert(converter = InfoObjectToStringConverter::class, dbType = String::class)
    var info: Info? = null,
    @Convert(converter = ResultsListToStringConverter::class, dbType = String::class)
    var results: MutableList<Result>? = null
)