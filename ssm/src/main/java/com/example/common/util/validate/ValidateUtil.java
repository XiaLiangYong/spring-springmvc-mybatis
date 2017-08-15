package com.example.common.util.validate;

import com.example.common.exception.BusinessException;
import com.example.common.exception.SystemException;
import com.example.common.util.date.DateUtil;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class ValidateUtil {
    public ValidateUtil() {
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new BusinessException(message);
        }
    }

    public static void isNotBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new BusinessException(message);
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new BusinessException(message);
        }
    }

    public static void isNotTrue(boolean expression, String message) {
        if (expression) {
            throw new BusinessException(message);
        }
    }

    public static void notEmpty(Collection<?> object, String message) {
        if (object == null || object.isEmpty()) {
            throw new BusinessException(message);
        }
    }

    public static void notEmpty(String str, String message) {
        if (str == null || str == "") {
            throw new BusinessException(message);
        }
    }

    public static Date isDate(String date, String format, String message) {
        if (date != null && date != "") {
            try {
                Date d = (new SimpleDateFormat(format)).parse(date);
                if (d == null) {
                    throw new BusinessException(message);
                } else {
                    return d;
                }
            } catch (ParseException var4) {
                throw new BusinessException(message);
            }
        } else {
            throw new BusinessException(message);
        }
    }

    public static void lt(Integer theNum, Integer diff, String message) {
        if (theNum != null && diff != null) {
            if (theNum.intValue() >= diff.intValue()) {
                throw new BusinessException(message);
            }
        } else {
            throw new BusinessException(message);
        }
    }

    public static void le(Integer theNum, Integer diff, String message) {
        if (theNum != null && diff != null) {
            if (theNum.intValue() > diff.intValue()) {
                throw new BusinessException(message);
            }
        } else {
            throw new BusinessException(message);
        }
    }

    public static void gt(Integer theNum, Integer diff, String message) {
        if (theNum != null && diff != null) {
            if (theNum.intValue() <= diff.intValue()) {
                throw new BusinessException(message);
            }
        } else {
            throw new BusinessException(message);
        }
    }

    public static void ge(Integer theNum, Integer diff, String message) {
        if (theNum != null && diff != null) {
            if (theNum.intValue() < diff.intValue()) {
                throw new BusinessException(message);
            }
        } else {
            throw new BusinessException(message);
        }
    }

    public static void strMaxLen(String str, int maxLength, String message) {
        if (str == null) {
            throw new SystemException("string is null");
        } else if (str.length() > maxLength) {
            throw new BusinessException(message);
        }
    }

    public static void strEqualLen(String str, int equalLength, String message) {
        if (str == null) {
            throw new SystemException("string is null");
        } else if (str.length() != equalLength) {
            throw new BusinessException(message);
        }
    }

    public static void booleanMaxLen(Boolean bln, int maxLength, String message) {
        if (bln == null) {
            throw new SystemException("boolean is null");
        } else if (1 > maxLength) {
            throw new BusinessException(message);
        }
    }

    public static void dateMaxLen(Date date, String format, int maxLength, String message) {
        if (date == null) {
            throw new SystemException("date is null");
        } else {
            String dateStr;
            try {
                dateStr = DateUtil.formatDate(date, format);
            } catch (Exception var6) {
                throw new SystemException("format is illegal");
            }

            if (dateStr.length() > maxLength) {
                throw new BusinessException(message);
            }
        }
    }
}

