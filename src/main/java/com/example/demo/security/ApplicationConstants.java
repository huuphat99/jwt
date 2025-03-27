package com.example.demo.security;

import java.util.List;

/**
 * whole application's constants class.
 *
 * @author quannl
 */

public final class ApplicationConstants {

    /**
     * Private constructor to prevent creating new object
     */
    private ApplicationConstants() {
    }

    /**
     * Session attribute of logged-in user
     */
    public static final String USER_REQUEST_ATTRIBUTE = "USER_INFO";

    /**
     * Datetime format 17 digits
     */
    public static final String DATETIME_SECOND_MILLISECOND_FORMAT = "yyyyMMddHHmmssSSS";

    /**
     * Time format mmddHHmmssSSS
     */
    public static final String TIME_SECOND_MILLISECOND_FORMAT = "MMddHHmmssSSS";

    /**
     * Datetime format 14 digits
     */
    public static final String DATETIME_SECOND_FORMAT = "yyyyMMddHHmmss";
    /**
     * Date format: yyyy/MM/dd
     */
    public static final String DATE_FORMAT = "yyyy/MM/dd";

    /**
     * Date format: yyyy/MM/dd HH:mm
     */
    public static final String DATETIME_LOCAL_FORMAT = "yyyy/MM/dd HH:mm";

    /**
     * Date format: yyyy/MM/dd HH:mmssSSS
     */
    public static final String DATETIME_LOCAL_MILLISECOND_FORMAT = "yyyy/MM/dd HH:mmssSSS";

    /**
     * Default message for unknown message id
     */

    public static final String UNDEFINED_ERROR_MESSAGE = "未定メッセージ";

    /**
     * Default Message ID
     */
    public static final String UNDEFINED_ERROR_MESSAGE_ID = "E000000";

    /**
     * Max value of temporary account sequence
     */
    public static final int MAX_TEMP_ACCOUNT_SEQUENCE = 999999;

    /**
     * Limit length sbcUserId
     */
    public static final Integer LIMIT_SBC_USER_ID = 20;

    /**
     * Regex check input is number
     */
    public static final String REGEX_NUMBER = "^\\d+$";

    /**
     * Return no record
     */
    public static final int NO_RECORD = 0;

    /**
     * Default digits gen password
     */
    public static final String DIGITS = "0123456789";

    /**
     * Default digits gen letters
     */
    public static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Default digits gen symbols
     */
    public static final String SYMBOLS = "!#$%";

    /**
     * Prefix static resource URL
     */
    public static final List<String> STATIC_RESOURCE_PREFIX_URLS = List.of("/assets", "/js");

    /**
     * Request attribute for logging start/end time
     */
    public static final String REQUEST_ATTRIBUTE_TIME = "requestTime";

    public static final String SAVE_MEMBER_API_URL = "/SaveMember.idPass";

    public static final String SEARCH_MEMBER_API_URL = "/SearchMember.idPass";

    public static final String SAVE_CARD_API_URL = "/SaveCard.idPass";

    public static final String SEARCH_CARD_API_URL = "/SearchCard.idPass";

    public static final String DELETE_CARD_API_URL = "/DeleteCard.idPass";

    public static final String CREATE_ORDER_API_URL = "/EntryTran.idPass";

    public static final String EXECUTE_PAYMENT_API_URL = "/ExecTran.idPass";

    public static final String LICENSE_EXPIRE_MONTH = "30";

    public static final String LICENSE_EXPIRE_TODAY = "0";

    public static final String LICENSE_EXPIRE_1_DAY = "1";

    public static final String LICENSE_EXPIRE_2_DAY = "2";

    public static final String LICENSE_EXPIRE_3_DAY = "3";

    public static final String LICENSE_EXPIRE_4_DAY = "4";

    public static final String FORMAT_TIME_JAPAN = "yyyy年MM月dd日";
}
