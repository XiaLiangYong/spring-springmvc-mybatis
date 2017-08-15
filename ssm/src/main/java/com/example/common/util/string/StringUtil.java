package com.example.common.util.string;


import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class StringUtil extends StringUtils {
    public static final String numberChar = "0123456789";

    public StringUtil() {
    }

    public static boolean isNull(String s) {
        return s == null;
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static String replaceBlankToCustom(String str, String symbol) {
        if (StringUtils.isBlank(str)) {
            return str;
        } else {
            String dest = "";
            if (str != null) {
                Pattern p = Pattern.compile("\\s+|\t|\r|\n");
                Matcher m = p.matcher(str);
                dest = m.replaceAll(symbol);
            }

            if (String.valueOf(dest.charAt(0)).equals(symbol)) {
                dest = dest.substring(1, dest.length());
            }

            if (String.valueOf(dest.charAt(dest.length() - 1)).equals(symbol)) {
                dest = dest.substring(0, dest.length() - 1);
            }

            return dest;
        }
    }

    public static String replaceBlank(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        } else {
            String dest = "";
            if (str != null) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(str);
                dest = m.replaceAll("");
            }

            return dest;
        }
    }

    public static String replaceTrnToBlank(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        } else {
            String dest = "";
            if (str != null) {
                Pattern p = Pattern.compile("\t|\r|\n");
                Matcher m = p.matcher(str);
                dest = m.replaceAll(" ");
            }

            return dest;
        }
    }

    public static String trimString(String str, String trimStr) {
        if (str != null && str.length() != 0 && trimStr != null && trimStr.length() != 0) {
            String regpattern = "[" + trimStr + "]*+";
            Pattern pattern = Pattern.compile(regpattern, 2);
            StringBuffer buffer = (new StringBuffer(str)).reverse();
            Matcher matcher = pattern.matcher(buffer);
            int epos;
            if (matcher.lookingAt()) {
                epos = matcher.end();
                str = (new StringBuffer(buffer.substring(epos))).reverse().toString();
            }

            matcher = pattern.matcher(str);
            if (matcher.lookingAt()) {
                epos = matcher.end();
                str = str.substring(epos);
            }

            return str;
        } else {
            return str;
        }
    }

    public static String toUpperCaseFirstChar(String str) {
        return str != null && str.length() != 0 ? (Character.isUpperCase(str.charAt(0)) ? str : Character.toUpperCase(str.charAt(0)) + str.substring(1)) : null;
    }

    public static String toLowerCaseFirstChar(String str) {
        return str != null && str.length() != 0 ? (Character.isLowerCase(str.charAt(0)) ? str : Character.toLowerCase(str.charAt(0)) + str.substring(1)) : null;
    }

    public static String generateMixString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < length; ++i) {
            sb.append("0123456789".charAt(random.nextInt("0123456789".length())));
        }

        return sb.toString();
    }

    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }
}

