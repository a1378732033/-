package com.online.edu.eduservice.entity.dto;

import lombok.Data;

@Data
public class CourseInfoDto {

    private  String id;  //课程Id
    private  String title; //课程标题
    private  String cover; //课程图标
    private  String price; //课程价格
    private  String description; //课程描述
    private  String teacherName; //课程教师名称
    private  String levelOne;    //课程一级分类
    private  String levelTwo;     //课程二级分类
}
