package com.online.edu.eduservice.controller;



import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduChapter;
import com.online.edu.eduservice.entity.dto.EduChapterDto;
import com.online.edu.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2019-04-20
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired

     private EduChapterService eduChapterService;
  //根据课程id查询章节和小节
    @GetMapping("/getChapterVideoList/{courseId}")
    public R getAllChapter(@PathVariable String courseId){
       List<EduChapterDto> list =eduChapterService.getAllChapterById(courseId);
       return  R.ok().data("items",list);
    }
    @PostMapping("addChapter")
    public  R addChapter(@RequestBody EduChapter eduChapter){
        boolean flag = eduChapterService.save(eduChapter);
        if (flag) {
            return R.ok();
        }else {
            return R.error().message("章节添加失败");
        }
    }
    @GetMapping("getChapter/{id}")
    public R getChapterById(@PathVariable String id){
        EduChapter eduChapter = eduChapterService.getById(id);
        return  R.ok().data("items",eduChapter);
    }
    @PostMapping("updateChapter")
    public  R updateChapter(@RequestBody EduChapter eduChapter){
        boolean flag = eduChapterService.updateById(eduChapter);
        if (flag) {
            return R.ok();
        }else {
            return R.error().message("修改章节失败");
        }
    }
    @DeleteMapping("{id}")
    public  R deleteChapter(@PathVariable String id){

        Boolean flag=eduChapterService.removeChapterById(id);
        if (flag) {
            return R.ok();
        }else {

            return R.error().message("删除的章节不存在");
        }
    }
}

