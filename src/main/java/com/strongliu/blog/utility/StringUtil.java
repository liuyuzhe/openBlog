package com.strongliu.blog.utility;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by liuyuzhe on 2017/2/25.
 */
public class StringUtil {

    public static List<Integer> StringToIntegerList(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }

        List<String> strList = Arrays.asList(str.split(","));
        return StringListToIntegerList(strList);
    }

    public static List<Integer> StringListToIntegerList(List<String> strList) {
        if (ObjectUtils.isEmpty(strList)) {
            return null;
        }

        List<Integer> iList = new ArrayList<>(strList.size());
        for (String str : strList) {
            iList.add(Integer.parseInt(str));
        }

        return iList;
    }

    public static String getUUID() {
        String str = UUID.randomUUID().toString();
        return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
    }
}
