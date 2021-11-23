package com.example.demo.trainExam.service;


import com.example.demo.common.doPage.PageQuery;
import com.example.demo.common.doPage.PageResult;
import com.example.demo.trainExam.controller.searchParam.ExamTopicSearchParam;
import com.example.demo.trainExam.model.ExamTopicBank;

import java.util.List;

/**
 * @author zcc
 * @date 2019/4/29 20:15
 */
public interface ExamTopicBankService {
    PageResult<ExamTopicBank> getPage(PageQuery pageQuery, ExamTopicSearchParam examTopicSearchParam);
    List<ExamTopicBank> getList();
    void insert(ExamTopicBank examTopicBank);
    void update(ExamTopicBank examTopicBank);
    void delete(List<Integer> ids);
}
