package com.online.edu.eduservice.mapper;

import com.online.edu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.online.edu.eduservice.entity.dto.CourseInfoDto;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2019-04-17
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
  //根据课程Id查询课程详情
    CourseInfoDto getCourseInfoAll(String courseId);
}
