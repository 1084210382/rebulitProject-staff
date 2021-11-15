package com.example.demo.trainExam.service;

import com.example.demo.common.doPage.PageQuery;
import com.example.demo.common.doPage.PageResult;
import com.example.demo.trainExam.controller.param.CaTopicParam;
import com.example.demo.trainExam.controller.param.ExamCaExamParam;
import com.example.demo.trainExam.controller.param.ExamCaTopic;
import com.example.demo.trainExam.controller.param.ExamExamParam;
import com.example.demo.trainExam.model.ExamExam;
import com.example.demo.trainExam.model.ExamSubject;

import java.util.List;
import java.util.Map;

/**
 * @author zcc
 * @date 2019/4/29 20:42
 */
public interface ExamExamService {
    PageResult<ExamExamParam> getPage(PageQuery pageQuery, ExamExamParam examExamParam);
    void insert(ExamExam examExam);
    void update(ExamExam examExam);
    void delete(int id);
    Map<String, Object> getExamTopicList(CaTopicParam caTopicParam);
    List<ExamCaExamParam> getWorkTypeExamList();
    void changeCaExam(int caId, int examId, ExamSubject examSubject, List<ExamCaTopic> examCaTopicList);
}
