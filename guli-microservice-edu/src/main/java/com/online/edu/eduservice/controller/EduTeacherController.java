package com.online.edu.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.query.QueryTeacher;
import com.online.edu.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author gujia
 * @since 2019-12-10
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    //查询所有教师
    @Autowired
    private EduTeacherService eduTeacherService;
    @GetMapping
    public R getAllTeacherList(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return  R.ok().data("items",list);
    }
    //删除教师
   @DeleteMapping("{id}")
    public R deleteById(@PathVariable String id){

       eduTeacherService.removeById(id);
       return R.ok();
   }
   //分页查询
    @GetMapping("pageList/{page}/{limit}")
    public  R getPageTeacherList(@PathVariable Long page,
                                 @PathVariable Long limit){
        Page<EduTeacher> pageTeacher=new Page<>(page,limit);
        eduTeacherService.page(pageTeacher, null);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total",total).data("items",records);
    }
    //带条件分页查询
    @PostMapping("moreCondtionPageList/{page}/{limit}")
    public  R getMoreCondtionPageList(@PathVariable Long page,
                                      @PathVariable Long limit,
                                      @RequestBody(required = false) QueryTeacher queryTeacher){
        Page<EduTeacher> eduTeacherPage =new Page<>(page,limit);
        eduTeacherService.pageListConditon(eduTeacherPage,queryTeacher);
        long total =eduTeacherPage.getTotal();
        List<EduTeacher> records = eduTeacherPage.getRecords();
        return R.ok().data("total",total).data("items",records);
    }
    //添加教师
    @PostMapping("addTeacher")
    public  R addTeacher( @RequestBody EduTeacher eduTeacher){

        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //根据Id查询讲师
    @GetMapping("getTeacher/{id}")
    public  R  getTeacherById(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return  R.ok().data("teacher",eduTeacher);
    }
    //修改讲师
    @PostMapping("updateTeacher/{id}")
    public  R updateTeacherById(@PathVariable String id,
                                @RequestBody EduTeacher eduTeacher){
        boolean update = eduTeacherService.updateById(eduTeacher);

      if (update){
        return  R.ok();
      }else {
          return  R.error();
      }

    }
}

