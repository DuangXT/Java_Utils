package com.duangxt.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sf.json.JSONObject;

/**
 * @author duangxt
 * @Title: TimeUtil.java
 * @Description: 时间处理工具类
 * @version V4.0
 */
public class Tools {

    private Tools(){}

    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    public static final String REGEX_CHINA_MOBILE_NUMBER_2017 = "^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$";
    public static final String REGEX_CHINA_MOBILE_NUMBER_2019 = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$";

    /**
     * 随机生成六位数字
     * @return
     */
    public static int getRandomNum(){
         return new Random().nextInt(900000)+100000;//(Math.random()*(999999-100000)+100000)
    }
    
    /**
     * if string value is a (null,"","null"), return false;<br/>
     * else return true.
     * @param s String
     * @return boolean
     */
    public static boolean notNull(String s){
        return s!=null && !"".equals(s.trim()) && !"null".equals(s.trim());
    }

    /**
     * if string value is a (null,"","null"), return true;<br/>
     * else return false.
     * @param s String
     * @return boolean
     */
    public static boolean isNull(String s){
        return MyStringUtils.isNull(s);
    }
    /**
     * if object is a (null,"","null"), return true;<br/>
     * if object is a new Object() or else, will return false.
     * @param o Object
     * @return boolean
     */
    public static boolean isNull(Object o){
        if(o==null) return true;
        try{
            String s = (String) o;
            s = s.trim();
            return isNull(s);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // === 兼容不同类型判断，避免全部走Object导致异常报错 ===
    public static boolean isEmpty(Long l) {
        return null == l || 0 == l ? true : false;
    }
    public static boolean isEmpty(Integer i) {
        return null == i || 0 == i ? true : false;
    }
    public static boolean isEmpty(Short s) {
        return null == s || s == 0 ? true : false;
    }
    public static boolean isEmpty(Double d) {
        return null == d || 0 == d ? true : false;
    }
    public static boolean isEmpty(Float f) {
        return null == f || 0 == f ? true : false;
    }
    public static boolean isEmpty(boolean b) {
        return b;
    }

    public static boolean isEmpty(double[] d) {
        return d.length == 0;
    }
    public static boolean isEmpty(Integer[] i) {
        return i.length == 0;
    }
    public static boolean isEmpty(int[] i) {
        return i.length == 0;
    }
    public static boolean isEmpty(Double[] d) {
        return d.length == 0;
    }
    public static boolean isEmpty(Float[] f) {
        return f.length == 0;
    }
    public static boolean isEmpty(float[] f) {
        return f.length == 0;
    }
    public static boolean isEmpty(boolean[] b) {
        return b.length == 0;
    }

    public static <T> boolean isEmpty(List<T> list){
        return null==list || list.isEmpty() || 0==list.size() || "[]".equals(list.toString());
    }
    public static <T> boolean isEmpty(ArrayList<T> list){
        return null==list || list.isEmpty() || 0==list.size()|| "[]".equals(list.toString());
    }
    public static boolean isEmpty(JSONObject list){
        return null==list || "".equals(list.toString()) || "{}".equals(list.toString()) || //"{}".equals(list.toJSONString()) ||
                1>list.size();
    }
    // === 兼容不同类型判，避免全部走Object导致异常报错 ===

    /**
     * 写txt里的单行内容
     * @param file_path String  文件路径
     * @param content String  写入的内容
     */
    public static void writeFile(String file_path,String content){
        String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";    //项目路径
        filePath = (filePath.trim() + file_path.trim()).substring(6).trim();
        if(filePath.indexOf(":") != 1){
            filePath = File.separator + filePath;
        }
        try {
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath),"utf-8");      
            BufferedWriter writer=new BufferedWriter(write);          
            writer.write(content);      
            writer.close(); 
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
      * 验证邮箱
      * @param email
      * @return
      */
    public static boolean checkEmail(String email){
        try{
            Matcher matcher = Pattern.compile(REGEX_EMAIL).matcher(email);
            return matcher.matches();
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

     /**
      * 验证中国手机号码
      * @param mobileNumber String
      * @return boolean
      */
    public static boolean checkMobileNumber_byChina(String mobileNumber){
        try{
            Matcher matcher = Pattern.compile(REGEX_CHINA_MOBILE_NUMBER_2019).matcher(mobileNumber);
            return matcher.matches();
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 读取txt里的单行内容
     * @param file_path  文件路径
     */
    public static String readTxtFile(String file_path) {
        try {
            
            String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";    //项目路径
            filePath = filePath.replaceAll("file:/", "");
            filePath = filePath.replaceAll("%20", " ");
            filePath = filePath.trim() + file_path.trim();
            if(filePath.indexOf(":") != 1) filePath = File.separator + filePath;

            String encoding = "utf-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {         // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file), encoding);    // 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    return lineTxt;
                }
                read.close();
            }
            else System.out.println("找不到指定的文件,查看此路径是否正确:"+filePath);
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 随机指定范围内N个不重复的数 <br/>
     * 在初始化的无重复待选数组中随机产生一个数放入结果中， <br/>
     * 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换 <br/>
     * 然后从len-2里随机产生下一个随机数，如此类推 <br/>
     * 例子：<pre>
     * &nbsp; &nbsp; randomSet(4, 7 , 3, set);  set中存在 4 5 6
     * &nbsp; &nbsp; randomSet(4, 6 , 2, set);  set中存在 4 5
     * </pre>
     * @param min  指定范围最小值 
     * @param max  指定范围最大值-1，即max=10,最大值为9
     * @param n  随机数个数 
     * @param set 随机数结果集（应该为空）
     * @return int[] 随机数结果集 
     */
    public static HashSet<Integer> randomSet(int min, int max, int n, HashSet<Integer> set) {
        if (n > (max - min + 1) || max < min) 
            return null;

        /*for (int i = 0; i < n; i++) {
            // 调用Math.random()方法  
            int num = (int) (Math.random() * (max - min)) + min;
            set.add(num);// 将不同的数存入HashSet中
        }
        int setSize = set.size();
        // 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小 
        if (setSize < n) 
            randomSet(min, max, n - setSize, set);// 递归
        */
        
        do {
            int num = (int) (Math.random() * (max - min)) + min;// 调用Math.random()方法  
            set.add(num);// 将不同的数存入HashSet中
        }while(set.size() < n);
        
        return set;
    }
}
