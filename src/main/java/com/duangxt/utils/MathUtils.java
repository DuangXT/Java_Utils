package com.duangxt.utils;

/**
 * @Author: duangxt
 * @Title:
 * @ClassName: MathUtils.java
 * @Package: com.duangxt.util
 * @Description:
 * @Date: Create in 2019/9/26 11:08:52
 * @Version: V0.0.2
 */
public class MathUtils {

    private MathUtils(){}

    private static final String TRUE_STR = "TRUE";
    private static final String FALSE_STR = "FALSE";

    private static String delNonum(String s){
        return s.replaceAll("[^\\d]","");
    }
    /** 删除字符串中非数字字符 */
    public static String deleteNoANumber(String s){
        return delNonum(s);
    }
    /** 处理字符串中的小数点，使之变成可以转换自然数的字符串 */
    private static String processPoint(String s){
        if (-1 != s.indexOf(".")) {
            if (s.startsWith(".")) s = "0" + s;
            if (s.endsWith(".")) s += "0";
            String s1 = s.substring(0, s.indexOf("."));
            String s2 = s.substring(s.indexOf("."), s.length());
            s2 = delNonum(s2);
            s1 = delNonum(s1);
            s = s1 + "." + s2;
        }
        else s = delNonum(s);
        return s;
    }

    // === 弱转换，一定要确定是特殊场景需要使用再用 ===
    /**
     * If string value is ( "000123" or "a1s2d3" or "123.456" or "123.456.789" or "~1@2#3..$4%5" )<br/>
     * Aways return integer value: 123
     * @param s String
     * @return integer
     */
    public static int parseInt(String s) throws NumberFormatException{
        s = MyStringUtils.deleteSpace(s);
        if ("".equals(s) || FALSE_STR.equals(s.toUpperCase())) return 0;
        if (TRUE_STR.equals(s.toUpperCase())) return 1;
        s = processPoint(s);
        if (-1 != s.indexOf(".")) s = s.substring(0, s.indexOf("."));
        return Integer.parseInt(s);
    }
    /**
     * If string value is ( "a1s2d3.f4g5h6" or "123.456" or "123.456.789" or "~1@2#3..$4%5" )<br/>
     * Aways return double value: 123.456
     * @param s String
     * @return double
     */
    public static double parseDouble(String s){
        s = MyStringUtils.deleteSpace(s);
        if ("".equals(s) || FALSE_STR.equals(s.toUpperCase())) return 0D;
        if (TRUE_STR.equals(s.toUpperCase())) return 1D;
        return Double.parseDouble(processPoint(s));
    }
    /**
     * If string value is ( "a1s2d3.f4g5h6" or "123.456" or "123.456.789" or "~1@2#3..$4%5" )<br/>
     * Aways return float value: 123.456
     * @param s String
     * @return float
     */
    public static float parseFloat(String s){
        s = MyStringUtils.deleteSpace(s);
        if ("".equals(s) || FALSE_STR.equals(s.toUpperCase())) return 0F;
        if (TRUE_STR.equals(s.toUpperCase())) return 1F;
        return Float.parseFloat(processPoint(s));
    }
    // === 弱转换end ===

}
