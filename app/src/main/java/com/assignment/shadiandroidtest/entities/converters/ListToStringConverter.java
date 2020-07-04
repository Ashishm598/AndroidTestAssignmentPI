package com.assignment.shadiandroidtest.entities.converters;

import com.assignment.shadiandroidtest.utils.CustomStringUtils;


import org.greenrobot.essentials.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.objectbox.converter.PropertyConverter;


public class ListToStringConverter implements PropertyConverter<List<String>, String> {

    @Override
    public List<String> convertToEntityProperty(String s) {
        List<String> result = new ArrayList<>();
        if (CustomStringUtils.isEmpty(s)) {
            return result;
        }
        Collections.addAll(result, s.split("\\|"));
        return result;

    }

    @Override
    public String convertToDatabaseValue(List<String> strings) {
        return StringUtils.join(strings, "|");
    }

    public static List<Long> convertStringListToLongList(List<String> strings) {
        List<Long> ids = new ArrayList<>();
        if (strings == null) {
            return ids;
        }
        for (String string : strings) {
            try {
                ids.add(Long.parseLong(string));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ids;
    }

    public static List<String> convertLongListToStringList(List<Long> longList) {
        List<String> stringList = new ArrayList<>();
        if (longList == null) {
            return stringList;
        }
        for (long current : longList) {
            try {
                stringList.add(String.valueOf(current));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringList;
    }

}
