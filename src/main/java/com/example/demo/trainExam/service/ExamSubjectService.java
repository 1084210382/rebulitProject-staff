package com.example.demo.trainExam.service;

import com.example.demo.common.doPage.PageQuery;
import com.example.demo.common.doPage.PageResult;
import com.example.demo.trainExam.controller.param.CaTopicParam;
import com.example.demo.trainExam.controller.param.ExamCaTopic;
import com.example.demo.trainExam.controller.param.ExamSubjectParam;
import com.example.demo.trainExam.controller.param.TopicNumParam;
import com.example.demo.trainExam.controller.searchParam.ExamSubjectSearchParam;
import com.example.demo.trainExam.model.ExamCaExam;
import com.example.demo.trainExam.model.ExamSubject;

import java.util.List;
import java.util.Map;

/**
 * @author zcc
 * @date 2019/4/29 20:54
 */
public interface ExamSubjectService {
    PageResult<ExamSubjectSearchParam> getPage(PageQuery pageQuery, ExamSubjectSearchParam examSubjectSearchParam);
    void insert(ExamSubject examSubject);
    void update(ExamSubject examSubject);
    void delete(int id);
    List<Integer> getSubjectTopicIds(int id);
    TopicNumParam getNum(ExamSubjectSearchParam examSubjectSearchParam);

    Map<String, Object> getExamTopicList(CaTopicParam caTopicParam);
    List<ExamSubjectParam> getWorkTypeExamList();
    ExamCaExam changeCaExam(int caId, ExamSubject examSubject, List<ExamCaTopic> examCaTopicList);
}
