package com.example.demo.trainExam.controller.param;


import com.example.demo.trainExam.model.ExamSubject;

/**
 * @author zcc
 * @date 2019/10/22 11:01
 */
public class ExamSubjectParam extends ExamSubject {
    private int examCaId;

    public int getExamCaId() {
        return examCaId;
    }

    public void setExamCaId(int examCaId) {
        this.examCaId = examCaId;
    }
}
