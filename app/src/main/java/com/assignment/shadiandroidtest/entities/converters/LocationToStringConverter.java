package com.assignment.shadiandroidtest.entities.converters;

import com.assignment.shadiandroidtest.entities.user.Id;
import com.assignment.shadiandroidtest.entities.user.Location;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.objectbox.converter.PropertyConverter;

public class LocationToStringConverter implements PropertyConverter<Location, String> {

    @Override
    public Location convertToEntityProperty(String databaseValue) {
        Location location = new Gson().fromJson(databaseValue, new TypeToken<Location>() {}.getType());
        return (location == null) ? new Location() : location;
    }

    @Override
    public String convertToDatabaseValue(Location location) {
        return new Gson().toJson(location);
    }
}

