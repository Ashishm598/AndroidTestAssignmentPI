package com.assignment.shadiandroidtest.entities.converters;

import com.assignment.shadiandroidtest.entities.user.Id;
import com.assignment.shadiandroidtest.entities.user.Location;
import com.assignment.shadiandroidtest.entities.user.Login;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.objectbox.converter.PropertyConverter;

public class LoginToStringConverter implements PropertyConverter<Login, String> {

    @Override
    public Login convertToEntityProperty(String databaseValue) {
        Login login = new Gson().fromJson(databaseValue, new TypeToken<Login>() {
        }.getType());
        return (login == null) ? new Login() : login;
    }

    @Override
    public String convertToDatabaseValue(Login login) {
        return new Gson().toJson(login);
    }
}

