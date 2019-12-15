package com.online.edu.eduservice.controller;



import com.online.edu.common.R;
import com.online.edu.eduservice.service.FileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@CrossOrigin
@RestController
@RequestMapping("/eduservice/oss")
public class FileController {
  @Autowired
   private FileService fileService;
  @PostMapping("file")
  @ApiOperation(value = "文件上传")
  public R uploadFile(
          @ApiParam(name = "file",value = "文件",required = true)
          @RequestParam("file") MultipartFile file ,String host) throws IOException {
        String uploadUrl = fileService.uploadFile(file,host);
      return  R.ok().message("文件上传成功").data("url",uploadUrl);
  }
}
