package com.example.demo.trainExam.service;


import com.example.demo.trainExam.controller.param.ExamCaTopic;

import java.util.List;

/**
 * @author zcc
 * @date 2019/4/29 22:12
 */
public interface ExamSubmitService {
    void changeCaSubmit(int caId, int examId, List<ExamCaTopic> examCaTopicList);
}
