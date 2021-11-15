package com.example.demo.trainExam.service.impl;

import com.example.demo.common.doPage.PageQuery;
import com.example.demo.common.doPage.PageResult;
import com.example.demo.trainExam.controller.param.ExamCaQualifyParam;
import com.example.demo.trainExam.controller.param.ExamEnquiryParam;
import com.example.demo.trainExam.controller.searchParam.ExamEnquirySearchParam;
import com.example.demo.trainExam.dao.ExamCaExamMapper;
import com.example.demo.trainExam.model.ExamCaExam;
import com.example.demo.trainExam.service.ExamCaExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zcc
 * @date 2019/4/29 21:52
 */
@Service
public class ExamCaExamServiceImpl implements ExamCaExamService {
    @Autowired
    private ExamCaExamMapper examCaExamMapper;

    @Override
    public PageResult<ExamEnquiryParam> getPage(PageQuery pageQuery, ExamEnquirySearchParam examEnquirySearchParam) {
        int count = examCaExamMapper.countList(examEnquirySearchParam);
        if (count > 0) {
            List<ExamEnquiryParam> examEnquiryParamList = examCaExamMapper.getPage(pageQuery, examEnquirySearchParam);
            PageResult<ExamEnquiryParam> pageResult = new PageResult<>();
            pageResult.setData(examEnquiryParamList);
            pageResult.setTotal(count);
            pageResult.setPageNo(pageQuery.getPageNo());
            pageResult.setPageSize(pageQuery.getPageSize());
            return pageResult;
        }
        PageResult<ExamEnquiryParam> pageResult = new PageResult<>();
        return pageResult;
    }

    @Override
    public List<ExamCaExam> getCaExamByCaId(int caId) {
        return (examCaExamMapper.getByCaId(caId));
    }

    @Override
    public ExamCaQualifyParam getCaQualify(int caId){
        return (examCaExamMapper.getCaQualify(caId));
    }
}
