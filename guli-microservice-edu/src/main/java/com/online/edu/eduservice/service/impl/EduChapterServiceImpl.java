package com.online.edu.eduservice.service.impl;




import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.eduservice.entity.EduChapter;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.entity.dto.EduChapterDto;
import com.online.edu.eduservice.entity.dto.EduVideoDto;
import com.online.edu.eduservice.handler.EduException;
import com.online.edu.eduservice.mapper.EduChapterMapper;
import com.online.edu.eduservice.service.EduChapterService;
import com.online.edu.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2019-04-20
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;
//根据课程id删除章节
    @Override
    public void deleteChapterByCourseId(String id) {
        QueryWrapper<EduChapter> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",id);
        baseMapper.delete(queryWrapper);
    }
//查询所有章节和小节
    @Override
    public List<EduChapterDto> getAllChapterById(String courseId) {

        //根据课程Id查询章节
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(queryWrapper);
        //根据课程Id查询小节
        QueryWrapper<EduVideo> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("course_id",courseId);
        List<EduVideo> eduVideoList = eduVideoService.list(queryWrapper1);
        List<EduChapterDto> list=new ArrayList<>();
        //遍历所有章节
        for (EduChapter eduChapter : eduChapterList) {
            EduChapterDto eduChapterDto = new EduChapterDto();
            BeanUtils.copyProperties(eduChapter, eduChapterDto);
            list.add(eduChapterDto);

             //遍历小节
             List<EduVideoDto> list1=new ArrayList<>();
            for (EduVideo eduVideo : eduVideoList) {
                if (eduVideo.getChapterId().equals(eduChapter.getId())){
                   EduVideoDto eduVideoDto=new EduVideoDto();
                   BeanUtils.copyProperties(eduVideo,eduVideoDto);
                   list1.add(eduVideoDto);
                }
            }
            eduChapterDto.setChildren(list1);
        }
        return list;
    }

    @Override
    public Boolean removeChapterById(String id) {
      List<EduVideo> eduVideo=eduVideoService.getVideoById(id);
        if (eduVideo.size()==0){
            int result = baseMapper.deleteById(id);
            return result>0;
        }else {
            throw new EduException(20001,"章节中含有小节不能直接删除");
        }

    }
}
