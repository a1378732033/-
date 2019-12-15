package com.online.edu.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.dto.CourseInfoDto;
import com.online.edu.eduservice.entity.dto.form.CourseInfoForm;
import com.online.edu.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2019-04-17
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/course")
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    //添加课程信息
    @PostMapping
    public R addCourseInfo(@RequestBody CourseInfoForm courseInfoForm) {
       String courseId= eduCourseService.insertCourseInfo(courseInfoForm);
       return R.ok().data("courseId",courseId);
    }
    //根据课程id查询课程信息
    @GetMapping("getCourseInfo/{id}")
    public  R getCourseInfoById(@PathVariable String id){
        CourseInfoForm courseInfoForm=eduCourseService.getIdCourse(id);
        return R.ok().data("courseInfoForm",courseInfoForm);
    }
    //修改课程方法
    @PostMapping("updateCourseInfo/{id}")
    public  R updateCourseInfoById(@PathVariable String id,
                                   @RequestBody CourseInfoForm courseInfoForm){
          Boolean flag= eduCourseService.updateCourse(courseInfoForm);
          if (flag) {
              return R.ok();
          }else {
              return  R.error();
          }
    }
    //课程列表
    @GetMapping("listCourse")
    public  R getCourseList(){
        List<EduCourse> eduCourseList = eduCourseService.list(null);
        return  R.ok().data("items",eduCourseList);
    }
    //带分页的条件查询
    @GetMapping("pageList/{page}/{limit}")
    public  R getPageCourseList(@PathVariable Long page,
                                @PathVariable Long limit){
        Page<EduCourse> pageCourse=new Page<>(page,limit);
        eduCourseService.page(pageCourse,null);
        long total = pageCourse.getTotal();
        List<EduCourse> records = pageCourse.getRecords();
        return R.ok().data("total",total).data("items",records);
    }
    @DeleteMapping("deleteCourse/{id}")
    public  R deleteCourseById(@PathVariable String id){

            Boolean flag=eduCourseService.removeCourseById(id);
            if (flag){
              return R.ok();
            }else {
                return R.error().message("删除课程失败");
            }
    }
    //根据课程Id查询课程详情信息
    @GetMapping("getAllCourseInfo/{courseId}")
    public  R getAllCourseInfo(@PathVariable String courseId){
           CourseInfoDto courseInfoDto=eduCourseService.getAllCourseInfo(courseId);
        return  R.ok().data("courseInfo",courseInfoDto);
    }
    @PostMapping("updateStatus/{courseId}")
    public R updateStatusById(@PathVariable String courseId){
      EduCourse eduCourse=new EduCourse();
      eduCourse.setId(courseId);
      eduCourse.setStatus("Normal");
      boolean flag = eduCourseService.updateById(eduCourse);
      if (flag) {
          return R.ok();
      }else {
          return R.error();
      }
    }
}

