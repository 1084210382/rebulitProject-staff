package com.example.demo.trainExam.service;


import com.example.demo.trainExam.controller.param.CaTopicParam;
import com.example.demo.trainExam.controller.param.ExamCaTopic;
import com.example.demo.trainExam.model.ExamSubject;
import com.example.demo.trainExam.model.ExamTopicBank;

import java.util.List;

/**
 * @author zcc
 * @date 2019/4/29 22:10
 */
public interface ExamSubjectTopicService {
    List<ExamTopicBank> getListBySubject(int SubjectId);
    List<ExamCaTopic> getCaListBySubject(CaTopicParam caTopicParam);
    void changeSubjectTopics(ExamSubject examSubject, List<ExamTopicBank> examTopicBankList);
    void deleteBySubjectId(int id);
}
