package com.assignment.shadiandroidtest.entities.converters;


import com.assignment.shadiandroidtest.entities.user.UserStatus;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.objectbox.converter.PropertyConverter;

public class UserStatusToStringConverter implements PropertyConverter<UserStatus, String> {

    @Override
    public UserStatus convertToEntityProperty(String databaseValue) {
        UserStatus userStatus = new Gson().fromJson(databaseValue, new TypeToken<UserStatus>() {
        }.getType());
        return (userStatus == null) ? new UserStatus() : userStatus;
    }

    @Override
    public String convertToDatabaseValue(UserStatus userStatus) {
        return new Gson().toJson(userStatus);
    }
}

