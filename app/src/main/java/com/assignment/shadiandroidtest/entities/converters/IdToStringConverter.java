package com.assignment.shadiandroidtest.entities.converters;

import com.assignment.shadiandroidtest.entities.user.Dob;
import com.assignment.shadiandroidtest.entities.user.Id;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.objectbox.converter.PropertyConverter;

public class IdToStringConverter implements PropertyConverter<Id, String> {

    @Override
    public Id convertToEntityProperty(String databaseValue) {
        Id id = new Gson().fromJson(databaseValue, new TypeToken<Id>() {}.getType());
        return (id == null) ? new Id() : id;
    }

    @Override
    public String convertToDatabaseValue(Id id) {
        return new Gson().toJson(id);
    }
}

