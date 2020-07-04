package com.assignment.shadiandroidtest.entities.converters;

import com.assignment.shadiandroidtest.entities.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.converter.PropertyConverter;

public class ResultsListToStringConverter implements PropertyConverter<List<Result>, String> {

    @Override
    public List<Result> convertToEntityProperty(String databaseValue) {
        List<Result> results = new Gson().fromJson(databaseValue, new TypeToken<List<Result>>() {
        }.getType());
        return (results == null) ? new ArrayList<>() : results;
    }

    @Override
    public String convertToDatabaseValue(List<Result> entityProperty) {
        return new Gson().toJson(entityProperty);
    }
}
