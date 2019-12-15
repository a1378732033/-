package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.EduCourseDescription;
import com.online.edu.eduservice.entity.dto.CourseInfoDto;
import com.online.edu.eduservice.entity.dto.form.CourseInfoForm;
import com.online.edu.eduservice.handler.EduException;
import com.online.edu.eduservice.mapper.EduCourseMapper;
import com.online.edu.eduservice.service.EduChapterService;
import com.online.edu.eduservice.service.EduCourseDescriptionService;
import com.online.edu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2019-04-17
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
   @Autowired
   private EduCourseDescriptionService eduCourseDescriptionService;
   @Autowired
   private EduChapterService eduChapterService;
   @Autowired
   private EduVideoService eduVideoService;
    @Override
    public String insertCourseInfo(CourseInfoForm courseInfoForm) {
        //添加课程基础信息
        //复制courseInfoForm
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int result = baseMapper.insert(eduCourse);
        if (result==0){
         throw new EduException(20001,"添加课程信息失败");
        }
        //添加课程描述信息
         EduCourseDescription eduCourseDescription=new EduCourseDescription();
         String courseId = eduCourse.getId();
         eduCourseDescription.setId(courseId);
         eduCourseDescription.setDescription(courseInfoForm.getDescription());
         boolean save = eduCourseDescriptionService.save(eduCourseDescription);
         if (!save){
             throw new EduException(20001,"添加课程描述信息失败");
         }
         return courseId;
    }

    @Override
    public CourseInfoForm getIdCourse(String id) {
        EduCourse eduCourse = baseMapper.selectById(id);
        if (eduCourse==null){
            throw new EduException(20001,"没有要查询的课程");
        }
         CourseInfoForm courseInfoForm=new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse,courseInfoForm);
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(id);
        courseInfoForm.setDescription(eduCourseDescription.getDescription());
        return courseInfoForm;
    }

    @Override
    public Boolean updateCourse( CourseInfoForm courseInfoForm) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int result = baseMapper.updateById(eduCourse);
        if (result==0){
            throw  new EduException(20001,"课程修改失败");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoForm,eduCourseDescription);
        boolean flag = eduCourseDescriptionService.updateById(eduCourseDescription);
        if (!flag){
            throw new EduException(20001,"课程修改失败");
        }
        return flag;
    }

    @Override
    public Boolean removeCourseById(String id) {
         //删除章节
         eduChapterService.deleteChapterByCourseId(id);
        //删除小节
        eduVideoService.deleteVideoByCourseId(id);
        //删除课程简介
        eduCourseDescriptionService.deleteCourseDescriptionId(id);
        //删除课程
        int result = baseMapper.deleteById(id);

        return result>0;
    }

    @Override
    public CourseInfoDto getAllCourseInfo(String courseId) {
        CourseInfoDto courseInfoAll = baseMapper.getCourseInfoAll(courseId);
        return courseInfoAll;
    }
}
