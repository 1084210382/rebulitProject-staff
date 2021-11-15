package com.example.demo.trainExam.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.demo.trainExam.model.ExamSubjectTopic;
import com.example.demo.trainExam.model.ExamTopicBank;

import com.example.demo.common.commonRequest.UserSession;
import com.example.demo.trainExam.controller.param.*;
import com.example.demo.trainExam.controller.searchParam.ExamSubjectSearchParam;
import com.example.demo.trainExam.dao.*;
import com.example.demo.trainExam.model.ExamCaExam;
import com.example.demo.trainExam.model.ExamSubject;
import com.example.demo.trainExam.service.ExamSubjectService;
import com.example.demo.trainExam.service.ExamSubjectTopicService;
import com.example.demo.trainExam.service.ExamSubmitService;
import com.example.demo.trainExam.service.ExamTrainCourseMaterialService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author zcc
 * @date 2019/4/29 22:11
 */
@Service
public class ExamSubjectTopicServiceImpl implements ExamSubjectTopicService {
    @Autowired
    private ExamSubjectTopicMapper examSubjectTopicMapper;
    @Autowired
    private ExamTopicBankMapper examTopicBankMapper;

    @Override
    public List<ExamTopicBank> getListBySubject(int SubjectId) {
        List<Integer> topicIdList = examSubjectTopicMapper.getTopicIdListBySubjectId(SubjectId);
        if (CollectionUtils.isEmpty(topicIdList)) {
            return Lists.newArrayList();
        }
        return examTopicBankMapper.getByIdList(topicIdList);
    }

    @Override
    public List<ExamCaTopic> getCaListBySubject(CaTopicParam caTopicParam) {
        int subjectId = caTopicParam.getSubjectId();
        List<Integer> topicIdList = examSubjectTopicMapper.getTopicIdListBySubjectId(subjectId);
        if (CollectionUtils.isEmpty(topicIdList)) {
            return Lists.newArrayList();
        }
        return examTopicBankMapper.getCaExamList(caTopicParam,topicIdList);
    }

    @Override
    public void changeSubjectTopics(ExamSubject examSubject, List<ExamTopicBank> examTopicBankList) {

//        List<Integer> originTopicIdList = examSubjectTopicMapper.getTopicIdListBySubjectId(subjectId);

        updateRoleUsers(examSubject, examTopicBankList);
    }

    @Override
    public void deleteBySubjectId(int id) {
        examSubjectTopicMapper.deleteBySubjectId(id);
    }

    @Transactional
    public void updateRoleUsers(ExamSubject examSubject, List<ExamTopicBank> examTopicBankList) {

        if (CollectionUtils.isEmpty(examTopicBankList)) {
            return;
        }

        List<ExamSubjectTopic> subjectTopicList = Lists.newArrayList();
        for (ExamTopicBank examTopicBank : examTopicBankList) {
            ExamSubjectTopic examSubjectTopic = new ExamSubjectTopic();
            examSubjectTopic.setSubjectId(examSubject.getId());
            examSubjectTopic.setTopicId(examTopicBank.getId());
            switch (examTopicBank.getType()){
                case 1:
                    examSubjectTopic.setScore(examSubject.getJudgementScore());
                    break;
                case 2:
                    examSubjectTopic.setScore(examSubject.getSingleScore());
                    break;
                case 3:
                    examSubjectTopic.setScore(examSubject.getMultipleScore());
                    break;
            }
            examSubjectTopic.setOperator("操作人");
            examSubjectTopic.setOperateIp("124.124.124");
            examSubjectTopic.setOperateTime(new Date());
            subjectTopicList.add(examSubjectTopic);
        }
        examSubjectTopicMapper.batchInsert(subjectTopicList);
    }
}
