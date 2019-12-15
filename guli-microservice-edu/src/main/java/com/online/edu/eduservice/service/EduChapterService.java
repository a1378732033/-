package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.dto.EduChapterDto;

import java.util.List;


/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2019-04-20
 */
public interface EduChapterService extends IService<EduChapter> {

    void deleteChapterByCourseId(String id);

    List<EduChapterDto> getAllChapterById(String courseId);

    Boolean removeChapterById(String id);

}
