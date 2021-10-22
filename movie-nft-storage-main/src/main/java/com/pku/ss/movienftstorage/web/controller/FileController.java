/**
 * @(#)FileController.java, 8月 16, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftstorage.web.controller;

import com.pku.ss.movienftstorage.constant.MovieNftStorageConstant;
import com.pku.ss.movienftstorage.dao.impl.FileStorageImpl;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


/**
 * @author zhangyan
 */
@Slf4j
@RestController
@RequestMapping(MovieNftStorageConstant.API + "file")
@Api(value = "文件相关的接口" , tags = "")
@CrossOrigin
public class FileController {

    @Autowired
    FileStorageImpl fileStorage;

    @PostMapping("/uopload-image")
    public String uploadFile(@RequestParam("file") MultipartFile file){
        return fileStorage.uploadFile(file);
    }

    @GetMapping("/download")
    public String downloadFile (HttpServletResponse response, @RequestParam("fileName") String fileName){
        return fileStorage.downloadFile(response,fileName);
    }

    @GetMapping("/hello")
    public String hello (){
        return "hello";
    }

}