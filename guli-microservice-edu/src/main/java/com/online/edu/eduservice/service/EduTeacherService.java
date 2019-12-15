package com.online.edu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.query.QueryTeacher;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author gujia
 * @since 2019-12-10
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void pageListConditon(Page<EduTeacher> eduTeacherPage, QueryTeacher queryTeacher);
}
