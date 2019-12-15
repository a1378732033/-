package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class userController {
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }
    @GetMapping("info")
    public R info(){
      return  R.ok().data("roles", "admin")
              .data("name", "admin")
              .data("avatar","https://gjluntan.oss-cn-beijing.aliyuncs.com/avatar/%E5%A4%B4%E5%83%8F.jpg");
    }
    @PostMapping("logout")
    public  R logout(){
        return R.ok();
    }
}
