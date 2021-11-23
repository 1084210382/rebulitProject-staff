package com.example.demo.trainExam.service;

import com.example.demo.common.doPage.PageQuery;
import com.example.demo.common.doPage.PageResult;
import com.example.demo.trainExam.controller.param.ExamCaQualifyParam;
import com.example.demo.trainExam.controller.param.ExamEnquiryParam;
import com.example.demo.trainExam.controller.searchParam.ExamEnquirySearchParam;
import com.example.demo.trainExam.model.ExamCaExam;

import java.util.List;

/**
 * @author zcc
 * @date 2019/4/29 21:52
 */
public interface ExamCaExamService {
    PageResult<ExamEnquiryParam> getPage(PageQuery pageQuery, ExamEnquirySearchParam examEnquirySearchParam);
    List<ExamCaExam>  getCaExamByCaId(int caId);
    ExamCaQualifyParam getCaQualify(int caId);
}
