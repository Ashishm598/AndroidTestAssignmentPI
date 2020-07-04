package com.assignment.shadiandroidtest.entities.converters;

import com.assignment.shadiandroidtest.entities.user.Picture;
import com.assignment.shadiandroidtest.entities.user.Registered;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.objectbox.converter.PropertyConverter;

public class RegisteredToStringConverter implements PropertyConverter<Registered, String> {

    @Override
    public Registered convertToEntityProperty(String databaseValue) {
        Registered registered = new Gson().fromJson(databaseValue, new TypeToken<Registered>() {
        }.getType());
        return (registered == null) ? new Registered() : registered;
    }

    @Override
    public String convertToDatabaseValue(Registered registered) {
        return new Gson().toJson(registered);
    }
}

