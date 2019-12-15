package com.online.edu.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.EduCourseDescription;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author testjava
 * @since 2019-04-17
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {

    void deleteCourseDescriptionId(String id);

}
