package com.online.edu.eduservice.controller;



import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2019-04-20
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;
   @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){

       boolean flag = eduVideoService.save(eduVideo);
       if (flag){
       return R.ok();
       }
       else {
        return R.error().message("添加小节失败");
       }
   }
   @GetMapping("{videoId}")
    public  R getVideoById(@PathVariable String videoId){

       EduVideo eduVideo = eduVideoService.getById(videoId);
       return R.ok().data("eduVideo",eduVideo);
   }
   @PostMapping("updateVideo")
    public  R updateVideo(@RequestBody EduVideo eduVideo){
       boolean flag = eduVideoService.updateById(eduVideo);
       if (flag) {
           return R.ok();
       }else {
           return  R.error().message("小节修改失败");
       }
   }
   @DeleteMapping("deleteVideo/{videoId}")
    public  R  deleteVideo(@PathVariable String videoId){

       Boolean flag=eduVideoService.removeVideo(videoId);
       if (flag) {
           return R.ok();
       }else {
          return R.error();
       }
   }

}

