package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2019-04-20
 */
public interface EduVideoService extends IService<EduVideo> {

    void deleteVideoByCourseId(String id);

    List<EduVideo> getVideoById(String id);

    Boolean removeVideo(String videoId);
}
