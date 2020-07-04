package com.assignment.shadiandroidtest.entities.converters;

import com.assignment.shadiandroidtest.entities.Info;
import com.assignment.shadiandroidtest.entities.Result;
import com.assignment.shadiandroidtest.utils.CustomStringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.converter.PropertyConverter;

public class InfoObjectToStringConverter implements PropertyConverter<Info, String> {

    @Override
    public Info convertToEntityProperty(String databaseValue) {
        Info info = new Gson().fromJson(databaseValue, new TypeToken<Info>() {}.getType());
        return (info == null) ? new Info() : info;
    }

    @Override
    public String convertToDatabaseValue(Info infoObject) {
        return new Gson().toJson(infoObject);
    }
}

