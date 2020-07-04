package com.assignment.shadiandroidtest.utils;

import android.text.TextUtils;

public class CustomStringUtils {

    public static boolean isEmpty(String value) {
        return TextUtils.isEmpty(value) || "null".equals(value);
    }

}
