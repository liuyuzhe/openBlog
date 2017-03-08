package com.strongliu.blog.utility;

/**
 * Created by liuyuzhe on 2017/2/27.
 */
public class RegularUtil {

    // 6-18位英文数字下划线
    public static final String USER_NAME = "^[A-Za-z0-9_-]{6,18}$";

    // 6-18位英文数字下划线
    public static final String PASSWORD = "^[A-Za-z0-9_-]{6,18}$";

    // 6-18位英文/中文数字下划线
    public static final String NICK_NAME = "^[A-Za-z0-9_-\\u4E00-\\u9FA5]{2,16}$";

    public static final String EMAIL = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\\.[a-zA-Z0-9_-]+$";

    public static final String MOBILEPHONE = "^1[3|4|5|7|8][0-9]{9}$";

    public static final String TELEPHONE = "^\\d{3}-\\d{8}|\\d{4}-\\d{7,8}$";

    public static final String URL = "^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$";


}
