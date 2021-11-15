package com.example.demo.trainExam.service;


import com.example.demo.common.doPage.PageQuery;
import com.example.demo.common.doPage.PageResult;
import com.example.demo.trainExam.controller.param.ExamCaTrainParam;
import com.example.demo.trainExam.controller.param.TrainCourseParam;
import com.example.demo.trainExam.controller.searchParam.ExamTrainCourseSearchParam;
import com.example.demo.trainExam.model.ExamTrainCourse;

import java.util.List;
import java.util.Map;

/**
 * @author zcc
 * @date 2019/4/29 21:25
 */
public interface ExamTrainCourseService {
    PageResult<TrainCourseParam> getPage(PageQuery pageQuery, ExamTrainCourseSearchParam examTrainCourseSearchParam);
    void insert(ExamTrainCourse examTrainCourse, List<Integer> materialIds);
    void update(ExamTrainCourse examTrainCourse, List<Integer> materialIds);
    void delete(int id);
    List<ExamCaTrainParam> getCaTrainList();
    Map<String, Object> getCourseMaterialIds(int courseId, int caId);
    Map<String, Object> getCourseIds(int caId);
}
