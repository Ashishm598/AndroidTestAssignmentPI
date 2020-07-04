package com.assignment.shadiandroidtest.entities.converters;

import com.assignment.shadiandroidtest.entities.user.Id;
import com.assignment.shadiandroidtest.entities.user.Login;
import com.assignment.shadiandroidtest.entities.user.Name;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.objectbox.converter.PropertyConverter;

public class NameToStringConverter implements PropertyConverter<Name, String> {

    @Override
    public Name convertToEntityProperty(String databaseValue) {
        Name name = new Gson().fromJson(databaseValue, new TypeToken<Name>() {
        }.getType());
        return (name == null) ? new Name() : name;
    }

    @Override
    public String convertToDatabaseValue(Name name) {
        return new Gson().toJson(name);
    }
}

