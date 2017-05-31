package com.strongliu.blog.utility;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
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

    public static String generatePostExcerpt(String content, int length) {
        if (StringUtils.isEmpty(content)) {
            return "";
        }

        if (content.length() <= length) {
            return content;
        }

        return content.substring(0, length);
    }

    public static String markdownToHtml(String markdown) {
        if (StringUtils.isEmpty(markdown)) {
            return "";
        }

        List<Extension> extensions = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder().extensions(extensions).build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().extensions(extensions).build();
        String content = renderer.render(document);
        return content;
    }
}
