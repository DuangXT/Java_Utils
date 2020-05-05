package com.duangxt.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**  
 * @Title: MyStringUtils.java
 * @Description: 字符串处理类
 * @author duangxt
 * @version V1.0
 */ 
public class MyStringUtils {

    private MyStringUtils(){}

    private static char[] str = {
            // 26个字母 小写
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',

            // 大写
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',

            // 10个阿拉伯数字
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',

            // 27个特殊字符 // 不能使用反斜杠 \ 避免出现转义问题
            '[', ']', '-', '_', '=', '+', '.', ',', '/', '@', '#', '$', '%',
            '!', '~', '(', ')', '&', '*', '<', '>', '?', '{', '}', ':', ';', '|'
    };

    public static final String REGEX_NUMBER_VALUE = "^[+|-]?[0-9]+(.[0-9]+)?$";
    public static final String REGEX_JAVA_SCIENTIFIC_COUNTING = "^[+|-]?[0-9]+(.[0-9]+)?([f|F|d|D|l|L]|([e|E]\\-?\\d+))?$";
    public static final String REGEX_LINE_FEED = "\r\n|\r|\n|\n\r|\\u000d";
    public static String REGEX_SPACE = REGEX_LINE_FEED + "| |　|\t|%20|\\u000a";

    /**
     * if string value is a (null,"","null"), return true;<br/>
     * else return false.
     * @param s String
     * @return boolean
     */
    public static boolean isNull(String s){
        s = deleteSpace(s);
        return "".equals(s) || "null".equals(s);
    }

    /**
     * if string value is a (null,"","null"), return false;<br/>
     * else return true.
     * @param s String
     * @return boolean
     */
    public static boolean isNotNull(String s){return !isNull(s);}

    /** try use this method and to avoid NullPointerException */
    public static String trim(StringBuffer sb){
        if (null == sb || "".equals(sb.toString())) return "";
        String s = sb.toString();
        int start = 0, end = s.length() - 1;
        while (start <= end && s.charAt(start) == ' ')
            start++;
        while (start <= end && s.charAt(end) == ' ')
            end--;
        return s.substring(start, end + 1);
    }
    /** try use this method and to avoid NullPointerException */
    public static String trim(String s){
        return (null == s || s.isEmpty() || "".equals(s.trim())) ? "" : s.trim();
    }

    /** 删掉字符串内的所有空格，只保留有效字符 */
    public static String deleteSpace(String s){
        return null==s ? "" : s.replaceAll(REGEX_SPACE,"");
    }

    /** 替换文本内的换行符为空格 */
    public static String deleteLineFeed(String s){
        return null==s || "".equals(s) ? "" : s.replaceAll(REGEX_LINE_FEED, " ");
    }

    /** 字符串是否是纯字母（不允许有空格） */
    public static boolean isLetter(String s){
        return null==s||"".equals(s)?false:"".equals(s.replaceAll("[A-Za-z]",""));
    }

    /** 字符串是否是纯小写字母（不允许有空格） */
    public static boolean isLowerCaseLetter(String s){
        return null==s||"".equals(s)?false:"".equals(s.replaceAll("[a-z]",""));
    }

    /** 字符串是否是纯大写字母（不允许有空格） */
    public static boolean isUpperCaseLetter(String s){
        return null==s||"".equals(s)?false:"".equals(s.replaceAll("[A-Z]",""));
    }

    /**
     * Converts the value of a string to a Boolean value<br/>
     * null, negative values, negative signs, etc. are false
     * <pre>
     * null "" "null" "false" "0" "-1.10" "-0.1"  "nil" "undefined" "-"  -> false
     * "1" "+1.1" "true" "+" or else string value                        -> true
     * </pre>
     */
    public static boolean toBoolean(String s) {
        s = deleteSpace(s).toLowerCase();
        if ("".equals(s)) return false;

        // 负数返回false
        if (0 == s.indexOf("-") && isNumberValue(s))
            return false;

        switch (s) {
            case "-":
                break;
            case "0":
                break;
            case "false":
                break;
            case "null":
                break;
            case "nil": // golang, lua
                break;
            case "undefined": // javascript
                break;
            case "nan": // Not a Number
                break;
            case "none": // python
                break;
            case "error":
                break;
            case "no":
                break;
			/*case "1":
			case "true":
			case "yes":*/
            default: // 其它字符串值，只要非空，都返回true
                return true;
        }
        return false;
    }

    /** 字符串是否是纯数字（不允许有空格） */
    public static boolean isNumber(String s){
        return null==s||"".equals(s)?false:"".equals(s.replaceAll("\\d",""));
    }

    /** 字符串是否是 Java 中的数值类型（包括科学计数法） */
    public static boolean isNumberValue(String s){
        s = deleteSpace(s); // 删除全部的空格
        return s.matches(REGEX_JAVA_SCIENTIFIC_COUNTING);
    }

    /** 字符串是否是自然数 */
    public static boolean isNaturalValue(String s){
        s = deleteSpace(s); // 删除全部的空格
        return s.matches(REGEX_NUMBER_VALUE);
    }

    /**
     * 去掉指定字符串的开头和结尾的指定字符
     * @param stream  要处理的字符串
     * @param trimstr 要去掉的字符串
     * @return String 处理后的字符串
     */
    public static String sideTrim(String stream, String trimstr) {
        // null或者空字符串的时候不处理
        if (isNull(stream) || isNull(trimstr))
            return null == stream ? "" : stream;

        // 结束位置
        int epos = 0;

        // 正规表达式
        String regpattern = "[" + trimstr + "]*+";
        Pattern pattern = Pattern.compile(regpattern, Pattern.CASE_INSENSITIVE);

        // 去掉结尾的指定字符
        StringBuffer buffer = new StringBuffer(stream).reverse();
        Matcher matcher = pattern.matcher(buffer);
        if (matcher.lookingAt()) {
            epos = matcher.end();
            stream = new StringBuffer(buffer.substring(epos)).reverse().toString();
        }

        // 去掉开头的指定字符
        matcher = pattern.matcher(stream);
        if (matcher.lookingAt()) {
            epos = matcher.end();
            stream = stream.substring(epos);
        }

        // 返回处理后的字符串
        return stream;
    }

    /**
     * 将以逗号分隔的字符串转换成字符串数组
     * @param valStr
     * @return String[]
     */
    public static String[] strList(String valStr){
        int i = 0;
        String tempStr = valStr;
        String[] returnStr = new String[valStr.length() + 1 - tempStr.replace(",", "").length()];
        valStr = valStr + ",";
        while (valStr.indexOf(',') > 0) {
            returnStr[i] = valStr.substring(0, valStr.indexOf(','));
            valStr = valStr.substring(valStr.indexOf(',')+1);

            i++;
        }
        return returnStr;
    }

    /**
     * 用默认的分隔符(,)将字符串转换为字符串数组
     * @param str
     * @return String[]
     */
    public static String[] str2StrArray(String str){
        return str2StrArray(str,",\\s*");
    }

    /**
     * 字符串转换为字符串数组
     * @param str 字符串
     * @param splitRegex 分隔符
     * @return String[]
     */
    public static String[] str2StrArray(String str,String splitRegex){
        if(null==str || "".equals(str) ||
                null==splitRegex || "".equals(splitRegex))
            return null;
        return str.split(splitRegex);
    }

    private static String UNDERLINE_REGEX = "_([a-z])";
    /** 下划线转换驼峰 */ // @source: https://blog.csdn.net/Lu_brave/article/details/94600318
    public static String underlineToHump(String str) {
        Matcher matcher = Pattern.compile(UNDERLINE_REGEX).matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find())
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 生成指定长度的随机字符串(一般是复杂密码用)
     * @param length int 字符串长度
     * @return String
     * @source: https://blog.csdn.net/Zhiyuan_Ma/article/details/52174366
     */
    public static String genRandomNum(int length) {
        // 35是因为数组是从0开始的，26个字母+10个数字
        // final int maxNum = 89; //36;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < length) {
            // 生成随机数，取绝对值，防止生成负数，
            i = Math.abs(r.nextInt(str.length)); // 生成的数最大为36-1
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }

    /**
     * 从可以正常访问的链接中获取实际域名<br/>
     * 例：<pre>
     *     https://www.abc.com/s?wd=a      ==>  www.abc.com
     *     http://127.0.0.1/myweb          ==>  127.0.0.1
     *     http://127.0.0.1:8080/myweb     ==>  127.0.0.1
     *     http://www.abc.com.cn/          ==>  www.abc.com.cn
     * </pre>
     * @param url
     * @return
     */
    public static String getUrlHostname(String url){
        return getUrlHostname(url, false);
    }

    public static String getUrlHostname(String url, boolean showProt){
        if("".equals(url = deleteSpace(url))) return "";

        if(0<url.indexOf("://"))
            url = url.substring(url.indexOf("://")+3);
        url = url.split("/")[0];
        if(!showProt) url = url.split(":")[0];

        return url;
    }

}
