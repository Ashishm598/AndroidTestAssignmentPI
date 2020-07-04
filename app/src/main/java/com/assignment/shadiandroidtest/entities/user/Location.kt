package com.assignment.shadiandroidtest.entities.user

data class Location(
    var city: String? = null,
    var coordinates: Coordinates? = null,
    var country: String? = null,
    var postcode: Any? = null,
    var state: String? = null,
    var street: Street? = null,
    var timezone: Timezone? = null
)