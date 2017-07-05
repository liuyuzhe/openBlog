package com.strongliu.blog.constant;

/**
 * Created by liuyuzhe on 2017/2/13.
 */
public class Constant {
    public static final int PAGE_INDEX_DEFAULT = 1;
    public static final int PAGE_SIZE = 10;
    public static final int PAGE_ADMIN_SIZE = 15;
    public static final int POST_EXCERPT_LENGTH = 30;

    public static final Integer DAY_TIME = 60 * 60 * 24;
    public static final String USER_SESSION_KEY = "login_user";
    public static final String USER_COOKIE_KEY = "userId";
    public static final String AES_SALT = "0123456789abcdef";
    public static final String AES_PASSWORD = "HelloWorld";

    public static final String POST_TYPE_DEFAULT = "post";
    public static final String POST_STATUS_DEFAULT = "publish";
    public static final String POST_COMMENT_STATUS_DEFAULT = "open";
    public static final long POST_COMMENT_COUNT = 0L;
    public static final long POST_READ_COUNT = 0L;
    public static final long POST_SPOT_COUNT = 0L;

    public static final int CATEGORY_POST_COUNT_DEFAULT = 0;
    public static final int TAG_POST_COUNT_DEFAULT = 0;
}
