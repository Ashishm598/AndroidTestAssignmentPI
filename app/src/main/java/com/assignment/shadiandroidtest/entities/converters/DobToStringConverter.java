package com.assignment.shadiandroidtest.entities.converters;

import com.assignment.shadiandroidtest.entities.user.Dob;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.objectbox.converter.PropertyConverter;

public class DobToStringConverter implements PropertyConverter<Dob, String> {

    @Override
    public Dob convertToEntityProperty(String databaseValue) {
        Dob dob = new Gson().fromJson(databaseValue, new TypeToken<Dob>() {}.getType());
        return (dob == null) ? new Dob() : dob;
    }

    @Override
    public String convertToDatabaseValue(Dob dob) {
        return new Gson().toJson(dob);
    }
}

