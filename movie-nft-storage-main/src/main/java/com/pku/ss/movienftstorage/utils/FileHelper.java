/**
 * @(#)FileHelper.java, 8月 27, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftstorage.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangyan
 */
@Slf4j
public class FileHelper {



    public static String getFileName(String imageName){
        return System.currentTimeMillis() + imageName;
    }

    public static String getNewFileName(String imageName) throws Exception{

            String[] fileArray = imageName.split(".");
            if(fileArray.length < 1 ) throw new Exception("Bad File name.");
            return String.valueOf(System.currentTimeMillis() + fileArray[1]);
    }

}