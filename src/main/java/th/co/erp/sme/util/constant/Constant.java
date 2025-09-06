package th.co.erp.sme.util.constant;


import java.time.format.DateTimeFormatter;

public class Constant {
    public static class CoreConstants {
        public static final String ISO8601_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
        public static final String FORMAT_ZONED_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ssXXX"; // 2020-01-21T00:00:00+07:00
        public static final String FORMAT_LOCAL_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss"; // 2020-01-21T00:00:00
        public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss"; // 2020-01-21 00:00:00
        public static final String FORMAT_LOCAL_DATE = "yyyy-MM-dd"; // 2020-01-21
        public static final String FORMAT_ISO_DATE = "yyyyMMdd";  // 20200121
        public static final String FORMAT_YEAR_DATE = "yyyyMM";  // 202001
        public static final String FORMAT_LOCAL_TIME = "HH:mm:ss"; // 00:00:00
        public static final String BANGKOK_ZONE = "Asia/Bangkok";
        public static final String BANGKOK_ICT = "+07:00";

        public static final String DEFAULT = "DEFAULT";
        public static final String SUCCESS = "SUCCESS";
    }
    public static class Format {
        public static final DateTimeFormatter FormatterYearDate = DateTimeFormatter.ofPattern(CoreConstants.FORMAT_ISO_DATE);
        public static final DateTimeFormatter FormatterDateTime = DateTimeFormatter.ofPattern(CoreConstants.FORMAT_DATE_TIME);
        public static final DateTimeFormatter FormatterExcelDate = DateTimeFormatter.ofPattern(CoreConstants.FORMAT_LOCAL_DATE);

    }

    public static String ROLE_LIST = "ROLE_LIST";
    public static String OBJECT_LIST = "OBJECT_LIST";
    public static String DELETED = "DELETED";
    public static String UPDATE = "UPDATE";
    public static String FLAG_Y = "Y";
    public static String FLAG_N = "N";

    public static String PRIVATE = "PRIVATE";
    public static String FRIEND_STR = "FRIEND";
    private Constant() {
        throw new IllegalArgumentException("THIS CLASS IS CONSTANT");
    }

    public static class FRIEND{

        private FRIEND(){
            throw new IllegalArgumentException("THIS CLASS IS CONSTANT");
        }
        public static String STATUS_FRIEND_SUCCESS = "SUCCESS";
        public static String STATUS_FRIEND_PENDING = "PENDING";
        public static String STATUS_FRIEND_CANCEL = "CANCEL";

    }

    public static class IgnoreField {
        private IgnoreField() {
        }

        public static final String UPDATE_BY = "updateBy";
        public static final String UPDATE_DATE ="updateDate";
    }


}
