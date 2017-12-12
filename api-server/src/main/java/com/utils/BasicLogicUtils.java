package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicLogicUtils {
	

    /**
     * 
     * @param productType 产品类型
     * @return ApplyNo 12位，产品类型+时间yyyyMMddHHmmss+"0"
     */
    public static String getApplyNo(String productType){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    	String appyNo=productType+sdf.format(new Date());
    	return appyNo;
    }
    
 }
