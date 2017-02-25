package com.strongliu.blog.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liuyuzhe on 2017/2/25.
 */
public class StringUtil {

    public static List<Integer> StringToIntegerList(String str) {
        List<String> strList = Arrays.asList(str.split(","));
        return StringListToIntegerList(strList);
    }

    public static List<Integer> StringListToIntegerList(List<String> strList) {
        List<Integer> iList = new ArrayList<>(strList.size());
        try {
            for (String str : strList) {
                iList.add(Integer.parseInt(str));
            }
        }
        catch (Exception e) {
        }

        return iList;
    }
}
